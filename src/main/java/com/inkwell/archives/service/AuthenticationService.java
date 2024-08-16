package com.inkwell.archives.service;

import com.inkwell.archives.model.UserEntity;
import com.inkwell.archives.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
  public UserEntity signup(UserEntity request) {
    // TODO? This method must have used jwt to provide authentication by using jwt, but for now, we are going to process users without it. We left this for later.
    UserEntity newUser = new UserEntity();

    newUser.setUserName(request.getUserName());
    newUser.setUserEmail(request.getUserEmail());
    newUser.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
    newUser.setUserAge(request.getUserAge());
    newUser.setUserCountry(request.getUserCountry());
    newUser.setUserCity(request.getUserCity());
    newUser.setUserAddress(request.getUserAddress());
    newUser.setRole(request.getRole());

    newUser = userRepository.save(newUser);

    return newUser;

  }

  // To login users
  public UserEntity authenticate(UserEntity request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUserEmail(),
                    request.getUserPassword()
            )
    );

    UserEntity userEmail = userRepository.findByUserEmail(request.getUserEmail()).orElseThrow();

    return userEmail;

  }
}

