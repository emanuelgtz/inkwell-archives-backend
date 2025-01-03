package com.inkwell.archives.service;

import com.inkwell.archives.model.PermissionEntity;
import com.inkwell.archives.model.RoleEntity;
import com.inkwell.archives.model.UserEntity;
import com.inkwell.archives.repository.RolesRepository;
import com.inkwell.archives.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final RolesRepository rolesRepository;

  @Autowired
  public AuthenticationService(
          UserRepository userRepository,
          PasswordEncoder passwordEncoder,
          AuthenticationManager authenticationManager,
          RolesRepository rolesRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.rolesRepository = rolesRepository;
  }


  // To create/register new users
  public UserEntity signup(UserEntity request) {
    UserEntity newUser = new UserEntity();

    List<RoleEntity> dbRoles = rolesRepository.findAll();
    List<RoleEntity> entry = request.getRole();

    List<RoleEntity> roles = dbRoles.stream()
            .filter(dbRole ->
                    entry.stream()
                    .anyMatch(userRole ->
                            userRole.getRoleName().equals(dbRole.getRoleName())))
            .collect(Collectors.toList());


    if(userRepository.findByUserEmail(request.getUserEmail()).isPresent()) {
      throw new RuntimeException(
              "The email inserted: ".concat(request.getUserEmail()) + " already exists");
    }
    
    // Create new user entity is the previous validation was false
    newUser.setUserName(request.getUserName());
    newUser.setUserEmail(request.getUserEmail());
    newUser.setUserPassword(
            passwordEncoder.encode(request.getUserPassword()));
    newUser.setUserAge(request.getUserAge());
    newUser.setUserCountry(request.getUserCountry());
    newUser.setUserCity(request.getUserCity());
    newUser.setUserAddress(request.getUserAddress());
    newUser.setRole(roles);
    newUser = userRepository.save(newUser);

    return newUser;
  }


  public UserEntity authenticate(UserEntity request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUserEmail(),
                    request.getUserPassword())
    );
    UserEntity userEmail =
            userRepository.findByUserEmail(
                    request.getUserEmail()).orElseThrow();
    return userEmail;
  };
};