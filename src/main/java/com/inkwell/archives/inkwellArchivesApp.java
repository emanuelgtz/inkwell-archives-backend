package com.inkwell.archives;

import com.inkwell.archives.model.PermissionEntity;
import com.inkwell.archives.model.RoleEntity;
import com.inkwell.archives.model.UserEntity;
import com.inkwell.archives.repository.PermissionRepository;
import com.inkwell.archives.repository.RolesRepository;
import com.inkwell.archives.repository.UserRepository;
import com.inkwell.archives.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

import java.awt.geom.Line2D;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class inkwellArchivesApp {
	// Creating Roles and their Permissions
	public static void main(String[] args) {
		SpringApplication.run(inkwellArchivesApp.class, args);
		System.out.println("Application is running");
	}



	@Bean
	public CommandLineRunner commandLineRunner(
					PermissionRepository permissionRepository,
					RolesRepository rolesRepository,
					UserRepository userRepository) {
		return args -> {

			// List to store permissions
			List<PermissionEntity> permissionsForAdminRole = new ArrayList<>();
			List<PermissionEntity> permissionsForUserRole = new ArrayList<>();
			// List to store roles
			List<RoleEntity> rolesListForAdmin = new ArrayList<>();
			List<RoleEntity> rolesListForUsers = new ArrayList<>();

			// Permissions for admin role
			PermissionEntity edit_content = PermissionEntity.builder()
							.permission("edit_content")
							.build();
			PermissionEntity remove_content = PermissionEntity.builder()
							.permission("remove_content")
							.build();
			PermissionEntity consult_purchase = PermissionEntity.builder()
							.permission("consult_purchase")
							.build();
			PermissionEntity consult_user_information = PermissionEntity.builder()
							.permission("consult_user_information")
							.build();


			permissionsForAdminRole.add(edit_content);
			permissionsForAdminRole.add(remove_content);
			permissionsForAdminRole.add(consult_purchase);
			permissionsForAdminRole.add(consult_user_information);

			// Permissions for user role
			PermissionEntity purchase = PermissionEntity.builder()
							.permission("purchase")
							.build();
			PermissionEntity update_personal_data = PermissionEntity.builder()
							.permission("update_personal_data")
							.build();
			PermissionEntity consult_self_purchase = PermissionEntity.builder()
							.permission("consult_self_purchase")
							.build();

			permissionsForUserRole.add(purchase);
			permissionsForUserRole.add(update_personal_data);
			permissionsForUserRole.add(consult_self_purchase);


			// Roles
			RoleEntity admin = RoleEntity.builder()
							.roleName("ADMIN")
							.permissionList(permissionsForAdminRole)
							.build();
			RoleEntity user = RoleEntity.builder()
							.roleName("USER")
							.permissionList(permissionsForUserRole)
							.build();

			/*permissionRepository.save(edit_content);
			permissionRepository.save(remove_content);
			permissionRepository.save(consult_purchase);
			permissionRepository.save(consult_user_information);
			permissionRepository.save(purchase);
			permissionRepository.save(update_personal_data);
			permissionRepository.save(consult_self_purchase);

			rolesRepository.save(admin);
			rolesRepository.save(user);*/

		};
	}

}
