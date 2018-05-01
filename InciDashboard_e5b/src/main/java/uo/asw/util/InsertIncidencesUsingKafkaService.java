package uo.asw.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import uo.asw.apacheKafka.producer.KafkaProducer;

@Service
public class InsertIncidencesUsingKafkaService {

	@Autowired
	private KafkaProducer kafkaProducer;

	int i = 1;
	
	// this will send a message to an endpoint on which a client can subscribe
	@Scheduled(fixedRate = 5000)
	public void trigger() {

		String identifier = UuidGenerator.getUuid();
		String jsonIncidenceWithOutTagFuego = "{"
				+ "\"identifier\": \""+ identifier + "\","
				+ "\"login\": \"31668313G\","
				+ "\"password\": \"1234\","
				+ "\"kind\": \"Person\","
				+ "\"name\": \"IncidenciaPrueba"+i+"\","
				+ "\"description\": \"Descripcion\","
				+ "\"tags\": [\"calor\"]"
			+ "}";
		
		String jsonIncidenceComplete = "{"
			+ "\"identifier\": \""+ identifier + "\","
    			+ "\"login\": \"31668313G\","
    			+ "\"password\": \"1234\","
    			+ "\"kind\": \"Person\","
    			+ "\"name\": \"Incidencia\","
    			+ "\"description\": \"Descripcion muy larga .............\","
    			+ "\"location\": \"43.35,-5.85\","
    			+ "\"tags\": [\"tag1\",\"tag2\"],"
    			+ "\"properties\": ["
    			+ "{\"prop1\": \"val1\"},"
    			+ "{\"prop2\": \"val2\"}"
    			+ "],"
    			+ "\"status\": \"open\","
    			+ "\"expiration\": \"14:60\""
			+ "}";

		if(i%2 == 0)
			kafkaProducer.send("incidences", jsonIncidenceWithOutTagFuego);
		else
			kafkaProducer.send("incidences", jsonIncidenceComplete);
		
		i++;
	}
	
}