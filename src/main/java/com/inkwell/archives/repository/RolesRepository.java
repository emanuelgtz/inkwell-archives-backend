package com.inkwell.archives.repository;

import com.inkwell.archives.model.PermissionEntity;
import com.inkwell.archives.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, Integer> {

  RoleEntity findById(int id);
  RoleEntity findByRoleName(String roleName);
  Set<PermissionEntity> findPermissionListByRole(RoleEntity roleName);

}
