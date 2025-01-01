package com.inkwell.archives.service.impl;

import com.inkwell.archives.model.PermissionEntity;
import com.inkwell.archives.model.RoleEntity;
import com.inkwell.archives.repository.PermissionRepository;
import com.inkwell.archives.service.interfaces.PermissionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PermissionServiceImpl implements PermissionService {

  private final PermissionRepository permissionRepository;
  @Autowired
  public PermissionServiceImpl(PermissionRepository permissionRepository) {
    this.permissionRepository = permissionRepository;
  }
  @Override
  public List<PermissionEntity> findAll() {
    return permissionRepository.findAll();
  }

  @Override
  public PermissionEntity findByPermissionId(int permissionId) {
    Optional<PermissionEntity> result = permissionRepository.findById(permissionId);
    PermissionEntity thePermission = null;

    if(result.isPresent()) {
      thePermission = result.get();
    } else {
      throw new RuntimeException("Finding the permission requested was not possible");
    }
    return thePermission;
  }

  @Override
  public PermissionEntity findByPermissionName(String permissionName) {

    Optional<PermissionEntity> result =
            permissionRepository.findByPermission(permissionName);

    PermissionEntity thePermissionName = null;

    if(result.isPresent()) {
      thePermissionName = result.get();
    } else {
      throw new RuntimeException("" +
              "Finding requested permission name was not possible");
    }
    return thePermissionName;
  }

  @Override
  @PostConstruct
  public PermissionEntity createPermissionDefault(
          PermissionEntity request,
          Set<RoleEntity> roleList) {


    PermissionEntity savedPermission = permissionRepository.save(request);

    return savedPermission;
  }
  @Override
  public PermissionEntity save(PermissionEntity thePermission) {
    return permissionRepository.save(thePermission);
  }
  @Override
  public void deleteByPermissionId(int id) {
    permissionRepository.deleteById(id);
  }
}
