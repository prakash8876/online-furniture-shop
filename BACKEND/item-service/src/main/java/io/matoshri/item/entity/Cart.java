package io.matoshri.item.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;
    @OneToMany
    private Set<Item> items = new HashSet<>();
    private LocalDate date;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToOne
    private Customer customer;
}
