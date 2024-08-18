package com.inkwell.archives;

import com.inkwell.archives.model.RoleEntity;
import com.inkwell.archives.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class inkwellArchivesApp {

	public static void main(String[] args) {
		SpringApplication.run(inkwellArchivesApp.class, args);

		System.out.println("Application is running");

		// TODO: When is is time to scrape data to work with books, we must add some edits to book table because we did not take int account the image. This is order to save the reference of the image that is going to be redirected to a server to get the stored image of each book.

	}


}
