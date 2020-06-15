package uz.marokand.market.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false,updatable=false)
    private Long id;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users customer;

    @OneToMany(targetEntity=OrderDetails.class, mappedBy="order",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    public List<OrderDetails> orderDetails;
    public Order() {
    }

    public Order(Date date, Users customer) {
        this.date = date;
        this.customer = customer;
    }



    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Users getCustomer() {
        return customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
    }



}