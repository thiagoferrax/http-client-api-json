package com.company.hcj;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.company.hcj.domains.tos.Event;
import com.company.hcj.domains.tos.Events;
import com.company.hcj.domains.tos.Partners;

class PartnersEventsTest {

	@Test
	void createObjectsFromJsonFile() throws IOException {
		assertNotNull(PartnersEvents.<Partners>createObjectsFromJsonFile("partners.json", Partners.class));
	}

	@Test
	void getEvents() throws IOException {
		Partners partners = PartnersEvents.<Partners>createObjectsFromJsonFile("partners.json", Partners.class);
		List<Event> partnerEvents = PartnersEvents.getEvents(partners);
	
		Events events = new Events();
		events.setEvents(partnerEvents);
		
		PartnersEvents.<Events>createJsonFileFromObject(events, "events.json");
		
		assertNotNull(partnerEvents);
	}

}
