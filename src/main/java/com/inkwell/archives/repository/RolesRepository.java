package com.inkwell.archives.repository;

import com.inkwell.archives.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, Integer> {

  RoleEntity findById(int id);
}
