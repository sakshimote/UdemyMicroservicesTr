package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.entity.WikimediaData;
import com.example.demo.repository.WikimediaRepository;

@Service
public class KafkaDatabaseConsumer {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	
	
	private WikimediaRepository wikimediaRepository;
	
	public KafkaDatabaseConsumer(WikimediaRepository wikimediaRepository) {
		this.wikimediaRepository=wikimediaRepository;
	}
	
	
	@KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
	public void consume(String eventMessage) {
		LOGGER.info(String.format("message recieved -> %s",eventMessage));
		WikimediaData wikimediaData=new WikimediaData();
		wikimediaData.setWikiEventData(eventMessage);
		wikimediaRepository.save(wikimediaData);
	}

}
