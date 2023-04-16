package io.matoshri.item.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "t_cart_details")
public class CartDetails {
    @Id
    @GeneratedValue
    private Long id;
    private Long customerId;
    @ElementCollection
    @CollectionTable(name = "cart_item_mapping",
    joinColumns = {@JoinColumn(name = "")})
    private Map<String, Integer> items = new HashMap<>();

    @OneToOne(mappedBy = "cartDetails")
    private Cart cart;
}
