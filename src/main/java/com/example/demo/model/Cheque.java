package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "cheque")
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;

    @JsonIgnoreProperties("cheques")
    @ManyToMany
    @JoinTable(
            name = "receipts",
            joinColumns = @JoinColumn(name = "chequeId"),
            inverseJoinColumns = @JoinColumn(name = "invoiceId")
    )
    @ToString.Exclude
    Set<Invoice> invoices;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cheque cheque = (Cheque) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
