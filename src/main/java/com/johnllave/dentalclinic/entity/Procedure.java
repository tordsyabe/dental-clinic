package com.johnllave.dentalclinic.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "procedures")
@ToString
public class Procedure extends BaseEntity {

	private LocalDate dateCreated;
	private String description;
	private Integer cost;
	private String paid = "unpaid";
	private String category;

	@OneToOne
	private Tooth tooth;

	@OneToMany(cascade = REMOVE, mappedBy = "procedure", orphanRemoval = true)
	private List<Invoice> invoices = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	public Procedure addInvoice(Invoice invoice) {
		invoice.setProcedure(this);
		this.invoices.add(invoice);

		return this;
	}

}
