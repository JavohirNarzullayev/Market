package uz.marokand.market.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "issued")
    private Date issued;
    @Column(name = "due")
    private Date due;

    public Invoice(Double amount, Date issued, Date due, Order order) {
        this.amount = amount;
        this.issued = issued;
        this.due = due;
        this.order = order;
    }

    public Invoice() {
    }
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id",referencedColumnName = "id",nullable = false)
    private Payment payment;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getIssued() {
        return issued;
    }

    public void setIssued(Date issued) {
        this.issued = issued;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}