package com.company.hcj.domains.tos;

import java.util.Date;
import java.util.List;

public class Partner {
	private String country;
	private String name;
	private List<Date> availability;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Date> getAvailability() {
		return availability;
	}

	public void setAvailability(List<Date> availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Partner [country=" + country + ", name=" + name + ", availability=" + availability + "]";
	}
	
	
}
