package com.inkwell.archives.service;

import com.inkwell.archives.model.AuthResponse;
import com.inkwell.archives.model.UserEntity;
import com.inkwell.archives.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  @Autowired
  public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  // To create/register new users
  public AuthResponse signup(UserEntity request) {
    UserEntity newUser = new UserEntity();

    newUser.setUserName(request.getUserName());
    newUser.setUserEmail(request.getUserEmail());
    return null;

  }


}
