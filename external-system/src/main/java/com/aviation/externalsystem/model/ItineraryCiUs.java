package com.aviation.externalsystem.model;

public class ItineraryCiUs {
	private String type;
	private String status;
	private String departure;
	private String arrival;
	private Covid covid;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Covid getCovid() {
		return covid;
	}

	public void setCovid(Covid covid) {
		this.covid = covid;
	}

}
