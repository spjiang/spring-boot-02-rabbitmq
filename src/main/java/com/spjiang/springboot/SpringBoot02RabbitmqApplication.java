package com.spjiang.springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jiangshengping
 */
@EnableRabbit
@SpringBootApplication
@RabbitListener
public class SpringBoot02RabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot02RabbitmqApplication.class, args);
	}

}
