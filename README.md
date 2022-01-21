# aviation

![alt text](https://github.com/Kafka-services/aviation/blob/main/Aviation.jpg)

### Topic creation
	kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic itinerary-C19
	kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic itinerary-C19-COUNTRY_AUS
	kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic itinerary-C19-COUNTRY_US

### Check no. of messages
	kafka-run-class.bat kafka.tools.GetOffsetShell --broker-list localhost:9092 --topic itinerary-C19
	kafka-run-class.bat kafka.tools.GetOffsetShell --broker-list localhost:9092 --topic itinerary-C19-COUNTRY_AUS
	kafka-run-class.bat kafka.tools.GetOffsetShell --broker-list localhost:9092 --topic itinerary-C19-COUNTRY_US

http://localhost:8070/itinerary/postItineraryAus

		{
			"type": "departure",
			"status": "landed",
			"departure": "2022-01-05T01:06:00.000",
			"arrival": "2022-01-05T01:06:00.000",
			"covid" : "2 dose must"
		}
    
    
http://localhost:8070/itinerary/postItineraryUs

		{
			"type": "arrival",
			"status": "transit",
			"departure": "2022-01-05T01:06:00.000",
			"arrival": "2022-01-05T01:06:00.000",
			"covid" : {
				"dose" : "2",
				"type" : "CoviShield"
			}
		}



