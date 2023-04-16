package io.matoshri.customer.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerOrders implements Serializable {
    private List<Order> customerOrders = new ArrayList<>();
}
