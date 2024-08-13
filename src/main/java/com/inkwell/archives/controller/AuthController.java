package com.inkwell.archives.controller;

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

  @PostMapping("/signup")
  public ResponseEntity<AuthResponse> signup(@RequestBody UserEntity request) {
    return null;
  }
}
