package com.inkwell.archives.controller;

import com.inkwell.archives.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import com.inkwell.archives.model.AuthResponse;
import com.inkwell.archives.model.UserEntity;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll")
public class AuthController {

  private final AuthenticationService authenticationService;

  @Autowired
  public AuthController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }


}
