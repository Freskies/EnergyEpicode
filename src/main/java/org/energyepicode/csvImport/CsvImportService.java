package org.energyepicode.csvImport;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.energyepicode.comune.Comune;
import org.energyepicode.comune.ComuneService;
import org.energyepicode.provincia.Provincia;
import org.energyepicode.provincia.ProvinciaService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.energyepicode.csvImport.*;


@Service
@Validated
@RequiredArgsConstructor
public class CsvImportService {
	private final ProvinciaService provinciaService;
	private final ComuneService comuneService;

	//import province
	public void importProvince (String csvFilePath) {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();
			String[] nextRecord;
			// Salta l'header
			csvReader.readNext();
			while ((nextRecord = csvReader.readNext()) != null) {
				String sigla = nextRecord[0];
				String nome = nextRecord[1];
				String regione = nextRecord[2];
				if (provinciaService.findBySigla(sigla).isEmpty()) {
					Provincia provincia = new Provincia();
					provincia.setNome(nome);
					provincia.setSigla(sigla);
					provincia.setRegione(regione);
					provinciaService.save(provincia);
				}
			}
		} catch (CsvValidationException | IOException e) {
			System.err.println("Errore durante la lettura del file CSV: " + e.getMessage());
		}
	}

	//import comuni
	public void importComuni (String csvFilePath) {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();
			String[] nextRecord;
			// Salta l'header
			csvReader.readNext();
			while ((nextRecord = csvReader.readNext()) != null) {
				String nomeComune = nextRecord[2];
				String nomeProvincia = nextRecord[3];
				Optional<Provincia> provinciaOpt = provinciaService.findByNome(nomeProvincia);
				if (provinciaOpt.isPresent()) {
					Comune comune = new Comune();
					comune.setNome(nomeComune);
					comune.setProvincia(provinciaOpt.get());
					comuneService.save(comune);
				} else {
					System.err.println("Provincia non trovata per nome: " + nomeProvincia);
				}
			}
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}
}
