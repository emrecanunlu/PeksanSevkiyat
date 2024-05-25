package com.replik.peksansevkiyat.DataClass.ListAdapter;

import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.Customer;
import com.replik.peksansevkiyat.DataClass.ModelDto.Order.Order;
import com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping.OrderShipping;

public class ListenerInterface {

    public interface ShipmentCustomerListener {
        void onItemClicked(Customer customer);
    }

    public interface OrderListener {
        void onItemCliked(Order order);
    }
    public interface OrderShippingListener {
        void onItemCliked(OrderShipping orderShipping);//OrderSipping
    }
}
