package com.fsmms.web_notification;

import com.fsmms.web_notification.domain.Message;
import com.fsmms.web_notification.services.interfaces.IWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	IWebSocketService webSocketService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}




	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Enter message to send: ");
			String input = scanner.nextLine();

			if ("exit".equalsIgnoreCase(input)) {
				System.out.println("Exiting loop...");
				break;
			}

			String[] message = input.split(" ", 2);
			if (Objects.equals(message[0], "0000"))
				webSocketService.send(new Message(message[1], false, "HTML"));
			else
				webSocketService.send(message[0], new Message(message[1], false, "HTML"));

		}

		System.exit(0);
	}
}
