package com.vbee.springbootmongodbnewspapersrestapi;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.vbee.springbootmongodbnewspapersrestapi.collections.Config;
import com.vbee.springbootmongodbnewspapersrestapi.config.AppConstant;
import com.vbee.springbootmongodbnewspapersrestapi.service.IConfigService;

@SpringBootApplication
@ComponentScan("com.vbee")
public class SpringbootMongodbNewspapersRestapiApplication implements CommandLineRunner {

	@Autowired
	IConfigService configService;

	@Autowired
	com.vbee.springbootmongodbnewspapersrestapi.model.Config configProperties;

	private static final Logger logger = LoggerFactory.getLogger(SpringbootMongodbNewspapersRestapiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbNewspapersRestapiApplication.class, args);
		logger.info("--Application Started--");
//		if (Ultils.ping("localhost", 27017, 300)) {
//			
//		} else {
//			try {
//				Process process = Runtime.getRuntime().exec(new String[] { "service", "mongod", "start" });
//				process.waitFor();
//				long start = System.currentTimeMillis();
//				while (System.currentTimeMillis() - start < 30000) {
//					if (Ultils.ping("localhost", 27017, 300)) {
//						SpringApplication.run(SpringbootMongodbNewspapersRestapiApplication.class, args);
//					} else {
//						Thread.sleep(100);
//					}
//				}
//			} catch (InterruptedException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("config version: " + configProperties.getConfigVersion());
		Config config = configService.findByVersion(configProperties.getConfigVersion());
		System.out.println("config: " + configProperties.getPort());
		if (config == null) {
			System.out.println("Database has not config version " + configProperties.getConfigVersion() + " !!!");
			return;
		}
		AppConstant.AUDIO_MAX = config.getCountVoiceAvaiable();
		System.out.println("Audio maximum on a article: " + AppConstant.AUDIO_MAX);
	}
}
