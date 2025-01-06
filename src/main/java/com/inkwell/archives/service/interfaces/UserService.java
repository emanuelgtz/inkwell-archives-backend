package com.inkwell.archives.service.interfaces;

import com.inkwell.archives.model.UserEntity;

import java.util.List;

public interface UserService {
  List<UserEntity> findAll();
  UserEntity findByUserId(int id);
  UserEntity findByUserEmail(String userEmail);
  UserEntity save(UserEntity theUser);
  void deleteByUserId(int id);

}
