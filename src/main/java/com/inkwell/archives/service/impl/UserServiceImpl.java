package com.inkwell.archives.service.impl;

import com.inkwell.archives.model.UserEntity;
import com.inkwell.archives.repository.UserRepository;
import com.inkwell.archives.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }

  @Override
  public UserEntity findByUserId(int id) {
    Optional<UserEntity> result = userRepository.findById(id);

    UserEntity theUserId = null;

    if(result.isPresent()) {
      theUserId = result.get();
    } else {
      throw new RuntimeException("Finding the requested use id was not possible" + id);
    }
    return theUserId;
  }

  @Override
  public UserEntity findByUserEmail(String userEmail) {
    Optional<UserEntity> result = userRepository.findByUserEmail(userEmail);

    UserEntity theUserEmail = null;


    if(result.isPresent()) {
      theUserEmail = result.get();
    } else {
      throw new RuntimeException("Finding requested user email was not possible " + userEmail);
    }
    return theUserEmail;
  }




  @Override
  public UserEntity save(UserEntity theUser) {
    return userRepository.save(theUser);
  }

  @Override
  public void deleteByUserId(int id) {
    userRepository.deleteById(id);
  }
}
