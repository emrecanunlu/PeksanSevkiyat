package com.replik.peksansevkiyat.Interface;

public interface Interfaces {
    interface OrderManuelQuantityInterface{
        void modalTick(Boolean statu, Double value);
    }
    interface BluetoothDeviceInterface{
        void selectedDevice(Boolean statu, String value);
    }
}
