package org.energyepicode.indirizzo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.energyepicode.comune.Comune;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "indirizzi")
public class Indirizzo {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Long id;

	private String via;
	private String civico;
	private String localita;
	private String cap;

	@ManyToOne
	@JoinColumn (name = "comune_id", nullable = false)
	private Comune comune;


}