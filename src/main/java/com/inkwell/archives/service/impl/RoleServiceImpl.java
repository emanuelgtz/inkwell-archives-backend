package com.inkwell.archives.service.impl;

import com.inkwell.archives.enums.PermissionEnum;
import com.inkwell.archives.enums.RoleEnum;
import com.inkwell.archives.model.PermissionEntity;
import com.inkwell.archives.model.RoleEntity;
import com.inkwell.archives.repository.RolesRepository;
import com.inkwell.archives.service.interfaces.PermissionService;
import com.inkwell.archives.service.interfaces.RoleService;
import jakarta.annotation.PostConstruct;
import org.hibernate.engine.jdbc.mutation.group.PreparedStatementDetails;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionCacheOptimizer;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
  private final RolesRepository rolesRepository;
  @Autowired
  public RoleServiceImpl(RolesRepository rolesRepository) {
    this.rolesRepository = rolesRepository;
  }

  @Override
  public List<RoleEntity> findAll() {
    return rolesRepository.findAll();
  }

  @Override
  public RoleEntity findByRoleId(int RoleId) {
    return rolesRepository.findById(RoleId);
  }

  @Override
  public RoleEntity createRoleDefault(RoleEntity request, Set<PermissionEntity> permissionList) {
    RoleEntity roleSaved = rolesRepository.save(request);
    return roleSaved;
  }

  @Override
  public RoleEntity saveRole(RoleEntity theRole) {
    return rolesRepository.save(theRole);
  }

  @Override
  public void deleteRoleById(int idRole) {
    rolesRepository.deleteById(idRole);
  }
}
