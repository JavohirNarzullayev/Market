package uz.marokand.market.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "time")
    private Timestamp time;

    @OneToMany(targetEntity=Invoice.class,fetch = FetchType.LAZY,mappedBy = "payment")
    private List<Invoice> invoiceList;
    public Payment() {
    }

    public Payment(Double amount, Timestamp time, List<Invoice> invoiceList) {
        this.amount = amount;
        this.time = time;
        this.invoiceList = invoiceList;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }


}

