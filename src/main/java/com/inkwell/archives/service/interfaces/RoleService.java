package com.inkwell.archives.service.interfaces;

import com.inkwell.archives.model.PermissionEntity;
import com.inkwell.archives.model.RoleEntity;

import java.util.List;
import java.util.Set;

public interface RoleService {
  List<RoleEntity> findAll();
  RoleEntity findByRoleId(int RoleId);
  RoleEntity createRoleDefault(RoleEntity role, Set<PermissionEntity> permissionList);
  RoleEntity saveRole(RoleEntity theRole);
  void deleteRoleById(int idRole);
}
