package com.company.hcj.domains.tos;

import java.util.Date;
import java.util.List;

public class Event {

	private String country;
	private List<String> joiners;
	private List<Date> dates;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getJoiners() {
		return joiners;
	}

	public void setJoiners(List<String> joiners) {
		this.joiners = joiners;
	}

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}

	@Override
	public String toString() {
		return "Event [country=" + country + ", joiners=" + joiners + ", dates=" + dates + "]";
	}

}
