//package uo.asw.util;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import uo.asw.apacheKafka.producer.KafkaProducer;
//
//@Service
//public class InsertIncidencesUsingKafkaService {
//
//	@Autowired
//	private KafkaProducer kafkaProducer;
//
//	@SuppressWarnings("serial")
//	@PostConstruct
//	public void init() {
//		
//		
//		Thread t = new Thread() {
//		    public void run() {
//		            executePruebas();
//		    }  
//		};
//
//		t.start();
//	
//	}
//
//	private void executePruebas() {
//		try {
//			Thread.sleep(15000);
//			
//			while(true) {
//			
//				int i = 1;
//				
//					Thread.sleep(7000);
//				
//				String identifier = UuidGenerator.getUuid();
//				String jsonIncidenceWithOutTagFuego = "{"
//			    		+ "\"identifier\": \""+ identifier + "\","
//			    		+ "\"login\": \"316683136\","
//			    		+ "\"password\": \"1234\","
//			    		+ "\"kind\": \"Person\","
//			    		+ "\"name\": \"IncidenciaPrueba"+i+"\","
//			    		+ "\"description\": \"Descripcion\","
//			    		+ "\"tags\": [\"calor\"]"
//			    	+ "}";
//				
//				kafkaProducer.send("incidences", jsonIncidenceWithOutTagFuego);
//			}
//			
//		}catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}