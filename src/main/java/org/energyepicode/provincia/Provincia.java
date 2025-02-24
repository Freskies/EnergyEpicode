package org.energyepicode.provincia;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "province")
public class Provincia {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;

	@Column (nullable = false)
	private String nome;

	@Column (nullable = false, unique = true)
	private String sigla;
}
