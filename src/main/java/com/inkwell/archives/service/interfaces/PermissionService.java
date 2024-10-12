package com.inkwell.archives.service.interfaces;

import com.inkwell.archives.model.PermissionEntity;
import com.inkwell.archives.model.RoleEntity;

import java.util.List;
import java.util.Set;

public interface PermissionService {
  List<PermissionEntity> findAll();
  PermissionEntity findByPermissionId(int permissionId);
  PermissionEntity findByPermissionName(String permissionName);
  PermissionEntity createPermissionDefault(PermissionEntity request, Set<RoleEntity> rolesList);

  PermissionEntity save(PermissionEntity thePermission);

  void deleteByPermissionId(int id);

}
