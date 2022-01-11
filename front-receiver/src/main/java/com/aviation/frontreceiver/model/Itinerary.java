package com.aviation.frontreceiver.model;

public class Itinerary {

	public Itinerary() {
		// TODO Auto-generated constructor stub
	}

	public Itinerary(String type, String status) {
		super();
		this.type = type;
		this.status = status;
	}

	private String type;
	private String status;

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

}
