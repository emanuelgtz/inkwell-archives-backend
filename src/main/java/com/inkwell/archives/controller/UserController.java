package com.inkwell.archives.controller;

import com.inkwell.archives.model.UserEntity;
import com.inkwell.archives.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }



  @GetMapping("/userinfo/{userEmail}")
  public ResponseEntity<UserEntity> getUserEntityByEmail(@PathVariable String userEmail) {
    Optional<UserEntity> currentUser =
            Optional.ofNullable(userService.findByUserEmail(userEmail));

    if (currentUser.isPresent()) {
      return ResponseEntity.ok(currentUser.get());
    }

    return ResponseEntity.notFound().build();

  }

}
