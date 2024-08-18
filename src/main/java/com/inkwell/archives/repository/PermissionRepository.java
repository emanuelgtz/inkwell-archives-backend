package com.inkwell.archives.repository;

import com.inkwell.archives.model.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
  Optional<PermissionEntity> findByPermission(String permissionName);
}
