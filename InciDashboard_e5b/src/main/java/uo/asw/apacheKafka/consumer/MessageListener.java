package uo.asw.apacheKafka.consumer;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import uo.asw.inciDashboard.filter.ReceiveIncidence;

@ManagedBean
public class MessageListener {

    private static final Logger logger = Logger.getLogger(MessageListener.class);

    @Autowired
	private ReceiveIncidence receiveIncidence;
    
    @KafkaListener(topics = "incidences")
    public void listen(String data) {
        logger.info("New message received: \"" + data + "\"");
        receiveIncidence.receiveIncidence(data);
    }



}
