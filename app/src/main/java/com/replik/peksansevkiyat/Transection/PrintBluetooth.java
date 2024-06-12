package com.replik.peksansevkiyat.Transection;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.replik.peksansevkiyat.DataClass.ModelDto.Label.ShippingPrintLabelDto;
import com.replik.peksansevkiyat.DataClass.ModelDto.Label.ZarfLabel;
import com.replik.peksansevkiyat.DataClass.ModelDto.Label.ZarfProducts;
import com.replik.peksansevkiyat.DataClass.ModelDto.TSPL.WidtHeight;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrintBluetooth extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;

    // needed for communication to bluetooth device / network
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;

    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;
    byte FONT_TYPE;

    public static String printer_id;

    public PrintBluetooth() {
    }

    public void findBT() {
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Toast.makeText(this, "Yazıcı Bulunamadı", Toast.LENGTH_SHORT).show();
            }
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
            }
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    if (device.getName().equals(printer_id)) {
                        mmDevice = device;
                        break;
                    } else {
                        mmDevice = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tries to open a connection to the bluetooth printer device
    public void openBT() throws IOException {
        try {
            // Standard SerialPortService ID
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            mmInputStream = mmSocket.getInputStream();
            beginListenForData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printQrCode(Bitmap qRBit) {
        try {
            PrintPic printPic1 = PrintPic.getInstance();
            printPic1.init(qRBit);
            byte[] bitmapdata2 = printPic1.printDraw();
            mmOutputStream.write(bitmapdata2);
            //mmOutputStream.write("\n".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printPalletLabel(String barcode) {
        final String pdfUrl = GlobalVariable.apiPdfUrl.concat("api/Generate/Palet?paletNo=" + barcode);

        try {
            String printData = "SIZE 75 mm,75 mm\nGAP 0 mm,0 mm\nCLS" +
                    "\nTEXT 70 mm,30 mm,\"3\",0,1.5 mm,1.5 mm,\"" + barcode + "\"" +
                    "\nQRCODE 70 mm,80 mm,\"1\",11,1,0,1,1,\"" + pdfUrl + "\"" +
                    "\nPRINT 1\nEND\n";
            mmOutputStream.write(printData.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTestTable(ZarfLabel label) {
        try {
            List<String> commands = new ArrayList<>();
            List<String> deliveryAddress = this.splitString(label.getTeslimAdresi(), 45);

            int totalWidth = 560;
            int totalHeight = 0;

            commands.add("SIZE 75 mm,75 mm");
            commands.add("GAP 0,0");
            commands.add("CLS");

            commands.add("TEXT 20,30,\"3\",0,1.25,1.25,\"{deliveryName}\"");
            commands.add("TEXT 20,65,\"3\",0,1.25,1.5,\"{transportName}\"");
            totalHeight += 85;

            for (int i = 0; i < deliveryAddress.size(); i++) {
                totalHeight += 28;
                String command = "TEXT 20," + totalHeight + ",\"3\",0,1,1,\"{text}\""
                        .replace("{text}", deliveryAddress.get(i));

                commands.add(command);
            }

            totalHeight += 60;

            commands.add("TEXT 20," + totalHeight + ",\"3\",0,1,1,\"Stok Kodu\"");
            commands.add("TEXT 230," + totalHeight + ",\"3\",0,1,1,\"Renk/Logo\"");
            commands.add("TEXT 480," + totalHeight + ",\"3\",0,1,1,\"Miktar\"");

            totalHeight += 40;

            commands.add("BAR 20," + totalHeight + "," + totalWidth + ",5");

            totalHeight += 20;

            for (int i = 0; i < label.getProducts().size(); i++) {
                commands.add("TEXT 20," + (totalHeight + i * 40) + ",\"3\",0,1,1,\"" + label.getProducts().get(i).getStokkodu() + "\"");
                commands.add("TEXT 230," + (totalHeight + i * 40) + ",\"3\",0,1,1,\"" + label.getProducts().get(i).getRenkLogo() + "\"");
                commands.add("TEXT 480," + (totalHeight + i * 40) + ",\"3\",0,1,1,\"" + label.getProducts().get(i).getMiktar() + "\"");
            }

            commands.add("PRINT 1");
            commands.add("END");

            final String raw = String.join("\n", commands)
                    .replace("{deliveryName}", this.getTruncatedString(label.getTeslimAdi(), 27))
                    .replace("{transportName}", this.getTruncatedString(label.getNakliyeTipi(), 27))
                    .replace("İ", "I")
                    .replace("ı", "i")
                    .replace("Ö", "O")
                    .replace("ö", "o")
                    .replace("Ü", "U")
                    .replace("ü", "u")
                    .replace("Ş", "S")
                    .replace("ş", "s")
                    .replace("Ç", "C")
                    .replace("ç", "c");

            mmOutputStream.write(raw.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTestLabel(ShippingPrintLabelDto shippingPrintLabelDto) {
        try {
            final String pdfUrl = GlobalVariable.apiPdfUrl.concat("api/Generate/Zarf?sevkNo=" + shippingPrintLabelDto.shippingNo);

            List<String> commands = new ArrayList<>();
            List<String> deliveryAddress = this.splitString(shippingPrintLabelDto.deliveryAddress, 45);

            int totalHeight = 0;

            commands.add("SIZE 75 mm,75 mm");
            commands.add("GAP 0,0");
            commands.add("CLS");
            commands.add("TEXT 10,48,\"3\",0,1.5,1.5,\"{deliveryName}\"");
            totalHeight += 60;
            for (int i = 0; i < deliveryAddress.size(); i++) {
                totalHeight += 28;
                final String text = "TEXT 10,{height},\"3\",0,1,1,\"{text}\""
                        .replace("{height}", String.valueOf(totalHeight))
                        .replace("{text}", deliveryAddress.get(i));

                commands.add(text);
            }
            totalHeight += 50;
            commands.add("TEXT " + String.valueOf(300 - (shippingPrintLabelDto.shippingNo.length() * 12)) + ", " + totalHeight + ",\"3\",0,1.5,1.5,\"{deliveryNo}\"");
            totalHeight += 40;
            commands.add("QRCODE 90," + totalHeight + ",\"1\",11,1,0,1,1,\"{deliveryPdfUrl}\"");
            commands.add("PRINT 1");
            commands.add("END");

            final String raw = String.join("\n", commands)
                    .replace("{deliveryName}", getTruncatedString(shippingPrintLabelDto.deliveryName, 27))
                    .replace("{deliveryAddress}", shippingPrintLabelDto.deliveryName)
                    .replace("{deliveryPdfUrl}", pdfUrl)
                    .replace("{deliveryNo}", shippingPrintLabelDto.shippingNo);


            mmOutputStream.write(raw.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> splitString(String input, int length) {
        List<String> parts = new ArrayList<>();
        if (input.length() > length) {
            int startIndex = 0;
            while (startIndex < input.length()) {
                // Uzunluk kadar bir parça al
                int endIndex = Math.min(startIndex + length, input.length());
                parts.add(input.substring(startIndex, endIndex));
                startIndex = endIndex;
            }
        } else {
            parts.add(input);
        }

        return parts;
    }

    public String getTruncatedString(String input, int length) {
        if (input.length() > length) {
            return input.substring(length - 3).concat("...");
        }

        return input;
    }

    public void printOrderLabel(String code) {
        try {
            mmOutputStream.write(code.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(byte[] data) {
        try {
            mmOutputStream.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printText(String str) {
        try {
            //String text = str + "\n";
            mmOutputStream.write(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * after opening a connection to bluetooth printer device,
     * we have to listen and check if a data were sent to be printed.
     */
    public void beginListenForData() {
        try {
            final Handler handler = new Handler();
            // this is the ASCII code for a newline character
            final byte delimiter = 10;
            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];
            workerThread = new Thread(new Runnable() {
                public void run() {
                    while (!Thread.currentThread().isInterrupted() && !stopWorker) {
                        try {
                            int bytesAvailable = mmInputStream.available();
                            if (bytesAvailable > 0) {
                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStream.read(packetBytes);
                                for (int i = 0; i < bytesAvailable; i++) {
                                    byte b = packetBytes[i];
                                    if (b == delimiter) {
                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer, 0,
                                                encodedBytes, 0,
                                                encodedBytes.length
                                        );
                                        // specify US-ASCII encoding
                                        final String data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;
                                        // tell the user data were sent to bluetooth printer device
                                        handler.post(new Runnable() {
                                            public void run() {
//                                                myLabel.setText(data);
                                            }
                                        });
                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }
                        } catch (IOException ex) {
                            stopWorker = true;
                        }
                    }
                }
            });
            workerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    this will update data printer name in ModelUser
    // close the connection to bluetooth printer.
    public void closeBT() throws IOException {
        try {
            stopWorker = true;
            mmOutputStream.close();
            mmInputStream.close();
            mmSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
