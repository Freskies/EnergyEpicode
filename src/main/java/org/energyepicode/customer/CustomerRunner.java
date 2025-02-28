package org.energyepicode.customer;


import lombok.RequiredArgsConstructor;
import org.energyepicode.faker.FakerConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Order(1)
public class CustomerRunner implements CommandLineRunner {

	private final CustomerService customerService;
	private final FakerConfig fakerConfig;

	@Override
	public void run (String... args) throws Exception {
		for (int i = 0; i < 5; i++) {
			Customer customer = new Customer();
			customer.setCompanyName(fakerConfig.faker().company().name());
			customer.setVatNumber(fakerConfig.faker().numerify("IT##########"));
			customer.setEmail(fakerConfig.faker().internet().emailAddress());
			customer.setInsertionDate(Date.from(Instant.now()));
			customer.setLastContactDate(Date.from(Instant.now()));
			customer.setAnnualTurnover(fakerConfig.faker().number().randomDouble(2, 10000, 100000));
			customer.setPec(fakerConfig.faker().internet().emailAddress());
			customer.setPhone(fakerConfig.faker().phoneNumber().cellPhone());
			customer.setEmailContact(fakerConfig.faker().internet().emailAddress());
			customer.setNameContact(fakerConfig.faker().name().firstName());
			customer.setSurnameContact(fakerConfig.faker().name().lastName());
			customer.setPhoneContact(fakerConfig.faker().phoneNumber().cellPhone());
			customer.setLogo(fakerConfig.faker().internet().url());
			customerService.save(customer);
		}
	}


}
