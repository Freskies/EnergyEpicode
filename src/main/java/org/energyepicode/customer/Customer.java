package org.energyepicode.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.energyepicode.fattura.Fattura;
import org.energyepicode.indirizzo.Indirizzo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "Customer")
public class Customer {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	private String companyName;
	private String vatNumber;
	private String email;

	@Temporal(TemporalType.DATE)
	private Date insertionDate;

	@Temporal(TemporalType.DATE)
	private Date lastContactDate;

	private Double annualTurnover;
	private String pec;
	private String phone;

	private String emailContact;
	private String nameContact;
	private String surnameContact;
	private String phoneContact;

	private String logo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "indirizzo_legale_id")
	private Indirizzo indirizzoLegale;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "indirizzo_operativo_id")
	private Indirizzo indirizzoOperativo;

	// Tipo: PA, SAS, SPA, SRL
	private String tipo;


	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Fattura> fatture = new ArrayList<>();


}