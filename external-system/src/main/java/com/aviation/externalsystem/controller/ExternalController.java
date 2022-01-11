package com.aviation.externalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aviation.externalsystem.constant.ApplicationConstant;
import com.aviation.externalsystem.kafka.config.producerrecord.MessageRecordProducer;
import com.aviation.externalsystem.kafka.configuration.KafkaProducer;
import com.aviation.externalsystem.model.Itinerary;
import com.aviation.externalsystem.model.ItineraryCiAus;
import com.aviation.externalsystem.model.ItineraryCiUs;

@RestController
@RequestMapping("/itinerary")
public class ExternalController {
	
	@Autowired
	KafkaProducer kafkaProducer;
	
	@PostMapping("/postmsg")
	public void postMessage(@RequestBody Itinerary itinerary) {
		kafkaProducer.sendMessage(itinerary);
	}
	
	
	
	
	@PostMapping("/postItineraryUs")
	public void postItineraryUs(@RequestBody ItineraryCiUs itineraryCiUs) {
		MessageRecordProducer producer = new MessageRecordProducer();
		producer.produceMessage(itineraryCiUs, ApplicationConstant.COUNTRY_US);
	}
	
	@PostMapping("/postItineraryAus")
	public void postItineraryAus(@RequestBody ItineraryCiAus itineraryCiAus) {
		MessageRecordProducer producer = new MessageRecordProducer();
		producer.produceMessage(itineraryCiAus, ApplicationConstant.COUNTRY_AUS);
	}
}
