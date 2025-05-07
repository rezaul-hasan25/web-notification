package com.fsmms.web_notification;

import com.fsmms.web_notification.domain.Message;
import com.fsmms.web_notification.services.WebSocketService;
import com.fsmms.web_notification.services.interfaces.IWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Scanner;

@SpringBootApplication
public class WebNotificationApplication implements CommandLineRunner {

	@Autowired
	IWebSocketService webSocketService;

	public static void main(String[] args) {
		SpringApplication.run(WebNotificationApplication.class, args);
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
			if (input.startsWith("222"))
				webSocketService.send("222", new Message(input, false,"HTML"));
			else if (input.startsWith("111"))
				webSocketService.send("111", new Message(input, false,"ACTION"));
			else if (input.startsWith("333"))
				webSocketService.send("333", new Message(input, false,"POPUP"));

		}

		System.exit(0);
	}
}
