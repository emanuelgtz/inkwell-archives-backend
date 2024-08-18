package com.inkwell.archives.dto;

import com.inkwell.archives.model.PermissionEntity;
import com.inkwell.archives.model.RoleEntity;
import java.util.HashSet;
import java.util.Set;

public class UserLoginDto {


  private String dtoEmail;
  private String dtoPassword;
  private RoleEntity dtRole;
  private Set<PermissionEntity> dtoPermissions = new HashSet<>();
  public UserLoginDto(String dtoEmail,
                      String dtoPassword, RoleEntity dtRole, Set<PermissionEntity> dtoPermissions) {
    this.dtoEmail = dtoEmail;
    this.dtoPassword = dtoPassword;
    this.dtRole = dtRole;
    this.dtoPermissions = dtoPermissions;
  }

  public String getDtoEmail() {
    return dtoEmail;
  }

  public void setDtoEmail(String dtoEmail) {
    this.dtoEmail = dtoEmail;
  }

  public String getDtoPassword() {
    return dtoPassword;
  }

  public void setDtoPassword(String dtoPassword) {
    this.dtoPassword = dtoPassword;
  }

  public RoleEntity getDtRole() {
    return dtRole;
  }

  public void setDtRole(RoleEntity dtRole) {
    this.dtRole = dtRole;
  }

  public Set<PermissionEntity> getDtoPermissions() {
    return dtoPermissions;
  }

  public void setDtoPermissions(Set<PermissionEntity> dtoPermissions) {
    this.dtoPermissions = dtoPermissions;
  }
}
