package org.energyepicode.fattura;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.energyepicode.customer.Customer;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "fatture")
public class Fattura {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date data;

	private Double importo;

	private String numero;

	//quello dinamico... da vedere ??
	private String stato;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
}