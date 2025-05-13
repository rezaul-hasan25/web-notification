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




	/// This method is testing purpose
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
				webSocketService.sendPublic(Message.builder()
								.message(message[1])
						.build());
			else
				webSocketService.send(message[0], Message.builder()
								.recipientId(message[0])
								.message(message[1])
						.build());

		}

		System.exit(0);
	}
}
