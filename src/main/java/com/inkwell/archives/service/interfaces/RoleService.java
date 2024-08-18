package com.inkwell.archives.service.interfaces;

import com.inkwell.archives.enums.RoleEnum;
import com.inkwell.archives.model.RoleEntity;
import com.inkwell.archives.model.UserEntity;

import java.util.List;

public interface RoleService {
  List<RoleEntity> findAll();

  RoleEntity findByRoleId(int id);

  RoleEntity findByRoleEnum(String roleEnum);
}
