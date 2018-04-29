package uo.asw.websocket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScheduleTask {

    @Autowired
    private SimpMessagingTemplate template;

    // this will send a message to an endpoint on which a client can subscribe
    @Scheduled(fixedRate = 5000)
    public void trigger() {
    	//Creao que aqui se envia la peticion correspondiente junto con la ahora a la 
    	//que se realizó dicha petición. ¿Esto puede ser asi?
        this.template.convertAndSend("/incidences/currentIncidences", new Date());
    }

}
