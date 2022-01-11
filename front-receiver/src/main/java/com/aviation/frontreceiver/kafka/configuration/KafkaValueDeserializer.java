package com.aviation.frontreceiver.kafka.configuration;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.aviation.frontreceiver.model.Itinerary;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaValueDeserializer implements Deserializer<Itinerary> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Itinerary deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(new String(data, "UTF-8"), Itinerary.class);
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
    }
}