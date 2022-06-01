package com.company.hcj;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.company.hcj.domains.tos.Partners;

class PartnersEventsTest {

	@Test
	void createObjectsFromJsonFile() throws IOException {
		assertNotNull(PartnersEvents.<Partners>createObjectsFromJsonFile("partners.json", Partners.class));
	}

	@Test
	void getEvents() throws IOException {
		Partners partners = PartnersEvents.<Partners>createObjectsFromJsonFile("partners.json", Partners.class);
		assertNotNull(PartnersEvents.getEvents(partners));
	}

}
