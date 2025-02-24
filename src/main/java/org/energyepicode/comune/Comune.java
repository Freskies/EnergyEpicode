package org.energyepicode.comune;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.energyepicode.provincia.Provincia;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "comuni")
public class Comune {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "provincia_id", nullable = false)
	private Provincia provincia;

}