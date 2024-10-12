package com.inkwell.archives;

import com.inkwell.archives.model.PermissionEntity;
import com.inkwell.archives.model.RoleEntity;
import com.inkwell.archives.repository.RolesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;

import java.util.Optional;

@SpringBootApplication
public class inkwellArchivesApp {

	public static void main(String[] args) {
		SpringApplication.run(inkwellArchivesApp.class, args);

		System.out.println("Application is running");

	}

}
