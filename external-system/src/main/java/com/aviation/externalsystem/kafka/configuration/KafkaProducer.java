package com.aviation.externalsystem.kafka.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.aviation.externalsystem.constant.ApplicationConstant;
import com.aviation.externalsystem.model.Itinerary;

@Component
public class KafkaProducer {
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(Itinerary itinerary) {
        
	    ListenableFuture<SendResult<String, Object>> future = 
	      kafkaTemplate.send(ApplicationConstant.TOPIC_NAME, itinerary);
		
	    future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

	        @Override
	        public void onSuccess(SendResult<String, Object> result) {
	            System.out.println("Sent message=[" + itinerary + 
	              "] with offset=[" + result.getRecordMetadata().offset() + "]");
	        }
	        @Override
	        public void onFailure(Throwable ex) {
	            System.out.println("Unable to send message=[" 
	              + itinerary + "] due to : " + ex.getMessage());
	        }
	    });
	}
}
