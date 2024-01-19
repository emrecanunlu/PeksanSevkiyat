package com.replik.peksansevkiyat.DataClass.ListAdapter;

import com.replik.peksansevkiyat.DataClass.ModelDto.Order.Order;
import com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping.OrderShipping;

public class ListenerInterface {
    public interface OrderListener {
        void onItemCliked(Order order);
    }
    public interface OrderShippingListener {
        void onItemCliked(OrderShipping orderShipping);//OrderSipping
    }
}
