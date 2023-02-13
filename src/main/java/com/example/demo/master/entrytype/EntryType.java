package com.example.demo.master.entrytype;

import com.example.demo.master.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "entry_type")
public class EntryType implements Serializable {

    @Id
    @SequenceGenerator(
            name = "entry_type_sequence",
            sequenceName = "entry_type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "entry_type_sequence"
    )
    @Column(
            name = "entry_type_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "entry_type_name",
            nullable = false
    )
    private String entryTypeName;
    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.PERSIST,
            mappedBy = "entryType",
            fetch = FetchType.EAGER
    )
    private List<Transaction> transactions = new ArrayList<>();

    public EntryType(String entryTypeName) {
        this.entryTypeName = entryTypeName;
    }

    public void addTransaction(Transaction transaction) {
        if(!this.transactions.contains(transaction)) {
            this.transactions.add(transaction);
            transaction.setEntryType(this);
        }
    }

    public void removeTransaction(Transaction transaction) {
        if(this.transactions.contains(transaction)) {
            this.transactions.remove(transaction);
            transaction.setEntryType(null);
        }
    }
}
