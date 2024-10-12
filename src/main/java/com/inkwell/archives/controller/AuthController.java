package com.inkwell.archives.controller;

import com.inkwell.archives.service.AuthenticationService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import com.inkwell.archives.model.AuthResponse;
import com.inkwell.archives.model.UserEntity;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

// We decided on setting the permission access in controllers by setting annotations.
@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll")
public class AuthController
{
  private final AuthenticationService authenticationService;
  @Autowired
  public AuthController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }
  @PreAuthorize("permitAll")
  @PostMapping("/signup")
  public ResponseEntity<UserEntity> signup(@RequestBody UserEntity request) {
    return ResponseEntity.ok(authenticationService.signup(request));
  }
  @PreAuthorize("permitAll")
  @PostMapping("/authenticate")
  public ResponseEntity<UserEntity> authenticate(
          @RequestBody UserEntity request) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }

}
