package org.energyepicode.fattura;


import lombok.RequiredArgsConstructor;
import org.energyepicode.customer.CustomerService;
import org.energyepicode.faker.FakerConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;

@Component
@RequiredArgsConstructor
@Order(2)
public class FatturaRunner implements CommandLineRunner {
	private final FatturaService fatturaService;
	private final FakerConfig fakerConfig;
	private final CustomerService customerService;

	@Override
	public void run (String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Fattura fattura = new Fattura();
			fattura.setImporto(fakerConfig.faker().number().randomDouble(2, 100, 1000));
			fattura.setNumero(fakerConfig.faker().numerify("###########"));
			fattura.setData(Date.from(Instant.now()));
			fattura.setStato(fakerConfig.faker().random().nextBoolean() ? "PAGATA" : "NON PAGATA");
			fattura.setCustomer(customerService.findById((long) Math.floor(Math.random() * 5)).orElse(null));
			fatturaService.save(fattura);
		}
	}


}
