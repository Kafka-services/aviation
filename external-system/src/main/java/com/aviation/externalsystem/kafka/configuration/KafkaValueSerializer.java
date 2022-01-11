package com.aviation.externalsystem.kafka.configuration;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.aviation.externalsystem.model.Itinerary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaValueSerializer implements Serializer<Itinerary> {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public byte[] serialize(String topic, Itinerary data) {
		try {
			return objectMapper.writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void close() {
	}
}
