package com.aviation.frontreceiver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aviation.frontreceiver.constant.ApplicationConstant;
import com.aviation.frontreceiver.kafka.config.consumerrecord.ConsumerLoop;

@SpringBootApplication
public class FrontReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontReceiverApplication.class, args);
		runThread(args);
	}
	
	
	public static void runThread(String[] args) { 
		  int numConsumers = 3;
		  String groupId = "group-id-json-1";
		  List<String> topics = Arrays.asList("itinerary-C19");
		  ExecutorService executor = Executors.newFixedThreadPool(numConsumers);

		  final List<ConsumerLoop> consumers = new ArrayList<>();
		  for (int i = 0; i < numConsumers; i++) {
		    ConsumerLoop consumer = new ConsumerLoop(i, groupId, topics);
		    consumers.add(consumer);
		    executor.submit(consumer);
		  }

		  Runtime.getRuntime().addShutdownHook(new Thread() {
		    @Override
		    public void run() {
		      for (ConsumerLoop consumer : consumers) {
		        consumer.shutdown();
		      } 
		      executor.shutdown();
		      try {
		        executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		    }
		  });
		}

}
