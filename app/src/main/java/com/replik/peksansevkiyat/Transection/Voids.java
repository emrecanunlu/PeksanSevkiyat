package com.replik.peksansevkiyat.Transection;

import static android.graphics.Color.BLACK;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Voids {

    public static Bitmap generateQr(String code, Integer w, Integer h) {
        if (!code.isEmpty()) {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(code, BarcodeFormat.QR_CODE, w, h);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static Bitmap textAsBitmap(String text, float textSize) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(BLACK);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

    public static Bitmap finalBitmap(Bitmap bmp1, float bm1Top, float bm1Left,
                                     Bitmap bmp2, float bm2Top, float bm2Left) {
        Bitmap bmOverlay = Bitmap.createBitmap(580, 600, bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, bm1Left, bm1Top, null);
        canvas.drawBitmap(bmp2, bm2Left, bm2Top, null);
        bmp1.recycle();
        bmp2.recycle();
        return bmOverlay;
    }

    public static Bitmap finalBitmap(Bitmap bmp1, float bm1Top, float bm1Left,
                                     Bitmap bmp2, float bm2Top, float bm2Left,
                                     Bitmap bmp3, float bm3Top, float bm3Left,
                                     Bitmap bmp4, float bm4Top, float bm4Left) {
        Bitmap bmOverlay = Bitmap.createBitmap(580, 600, bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, bm1Left, bm1Top, null);
        canvas.drawBitmap(bmp2, bm2Left, bm2Top, null);
        canvas.drawBitmap(bmp3, bm3Left, bm3Top, null);
        canvas.drawBitmap(bmp4, bm4Left, bm4Top, null);
        bmp1.recycle();
        bmp2.recycle();
        return bmOverlay;
    }

    public static String formatDate(String dateString) {
        // Giriş formatını belirleyin
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
        // Çıkış formatını belirleyin
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Tarihi giriş formatına göre parse edin
            Date date = inputFormat.parse(dateString);
            // Tarihi çıkış formatında formatlayın
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Geçersiz Tarih";
        }
    }
}
