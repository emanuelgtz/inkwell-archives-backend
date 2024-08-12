package com.inkwell.archives;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class inkwellArchivesApp {

	public static void main(String[] args) {
		SpringApplication.run(inkwellArchivesApp.class, args);

		System.out.println("Application is running");

		// TODO: When is is time to scrape data to work with books, we must add some edits to book table because we did not take int account the image. This is order to save the reference of the image that is going to be redirected to a server to get the stored image of each book.


	}

}
