package com.inkwell.archives.repository;

import com.inkwell.archives.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, Integer> {

  Optional<RoleEntity> findRoleEntityByRolename(String rolename);

}
