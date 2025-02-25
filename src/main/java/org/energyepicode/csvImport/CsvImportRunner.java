package org.energyepicode.csvImport;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CsvImportRunner implements CommandLineRunner {
	private final CsvImportService csvImportService;

	@Override
	public void run (String... args) throws Exception {

		String provinceCsvPath = "src/main/resources/csv/province-italiane.csv";
		String comuniCsvPath = "src/main/resources/csv/comuni-italiani.csv";

		// Chiamo i metodi
		csvImportService.importProvince(provinceCsvPath);
		csvImportService.importComuni(comuniCsvPath);
	}


}
