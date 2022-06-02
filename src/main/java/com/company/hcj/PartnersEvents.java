package com.company.hcj;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.company.hcj.domains.tos.Event;
import com.company.hcj.domains.tos.Partner;
import com.company.hcj.domains.tos.Partners;
import com.google.gson.Gson;

public class PartnersEvents {

	private static Comparator<? super Date> comparator = (date1, date2) -> date1.after(date2) ? 1
			: date2.after(date1) ? -1 : 0;

	public static List<Event> getEvents(Partners partners) {

		Map<String, Map<Date, List<String>>> eventMap = buildEventMap(partners.getPartners());

		List<Event> events = new ArrayList<>();
		eventMap.forEach((country, availabilityMap) -> {

			Event event = new Event();
			event.setCountry(country);

			for (Date date : availabilityMap.keySet()) {
				Date nextDay = addOneDay(date);

				List<String> joiners1stDay = availabilityMap.get(date);
				List<String> joiners2ndDay = availabilityMap.get(nextDay);

				if (joiners2ndDay == null) {
					continue;
				}

				joiners1stDay.retainAll(joiners2ndDay);

				List<String> currentJoiners = event.getJoiners();
				if (currentJoiners == null) {
					event.setDates(getEventDates(date, nextDay));
					event.setJoiners(joiners1stDay);
				} else if (joiners1stDay.size() > currentJoiners.size()) {
					event.setDates(getEventDates(date, nextDay));
					event.setJoiners(joiners1stDay);
				}
			}

			events.add(event);
		});

		return events;
	}

	private static List<Date> getEventDates(Date date, Date nextDay) {
		List<Date> eventDates = new ArrayList<>();
		eventDates.add(date);
		eventDates.add(nextDay);
		return eventDates;
	}

	private static Map<String, Map<Date, List<String>>> buildEventMap(List<Partner> partnersList) {
		Map<String, Map<Date, List<String>>> eventMap = new HashMap<>();
		partnersList.forEach(partner -> buildEventMap(eventMap, partner));
		return eventMap;
	}

	private static void buildEventMap(Map<String, Map<Date, List<String>>> eventMap, Partner partner) {
		partner.getAvailability().forEach(date -> {
			Map<Date, List<String>> availabilityMap = eventMap.get(partner.getCountry());

			if (availabilityMap == null) {
				availabilityMap = new TreeMap<>(PartnersEvents.comparator);
				availabilityMap.put(date, newJoinerList(partner.getName()));
				eventMap.put(partner.getCountry(), availabilityMap);
			} else {
				List<String> joiners = availabilityMap.get(date);
				if (joiners == null) {
					availabilityMap.put(date, newJoinerList(partner.getName()));
				} else {
					joiners.add(partner.getName());
				}
			}
		});
	}

	private static List<String> newJoinerList(String name) {
		List<String> joiners = new ArrayList<>();
		joiners.add(name);
		return joiners;
	}

	public static <T> T createObjectsFromJsonFile(String path, Class<T> clazz) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(path));
		return new Gson().fromJson(reader, clazz);
	}

	public static Date addOneDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}
}
