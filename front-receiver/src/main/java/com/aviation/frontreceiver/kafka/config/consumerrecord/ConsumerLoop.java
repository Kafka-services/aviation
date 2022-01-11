package com.aviation.frontreceiver.kafka.config.consumerrecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsumerLoop implements Runnable {
	private final KafkaConsumer<String, String> consumer;
	private final List<String> topics;
	private final int id;
	private final AdminClient admin;

	public ConsumerLoop(int id, String groupId, List<String> topics) {
		this.id = id;
		this.topics = topics;
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "group-id-json-1");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		this.consumer = new KafkaConsumer<>(props);
		
		//Admin Client to check the existance of topic
		admin = AdminClient.create(props);
	}

	@Override
	public void run() {
		try {
			consumer.subscribe(topics);

			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
				for (ConsumerRecord<String, String> record : records) {
					Map<String, Object> data = new HashMap<>();
					data.put("partition1", record.partition());
					data.put("offset", record.offset());
					data.put("value", record.value());
					
					System.out.println("###################################==========>"+ record.topic());
					System.out.println("###################################==========>"+ record.key());
					
					Headers headers = record.headers();
					//System.out.println("====================>"+ headers);
					for (Header header : headers) {
	                    System.out.println(header.key());
	                    System.out.println(header.value());
	                }
					
					System.out.println(this.id + " : " + data);
					
					//Checking topic names
					try {
						boolean topicExists = admin.listTopics().names().get().stream().anyMatch(topicName -> topicName.equalsIgnoreCase(record.key()));
						System.out.println(record.key() +" ---> " + topicExists);
						
						if(topicExists) {
							ObjectMapper mapper = new ObjectMapper();
							Object obj = mapper.readValue(record.value(), Object.class);
							System.out.println("--------------->" + obj);
							
							MessageRecordProducer producer = new MessageRecordProducer();
							producer.produceMessage(obj, record.key());
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		} catch (WakeupException e) {
			// ignore for shutdown
		} finally {
			consumer.close();
		}
	}

	public void shutdown() {
		consumer.wakeup();
	}
}