package com.inkwell.archives.dto;

import com.inkwell.archives.model.PermissionEntity;
import com.inkwell.archives.model.RoleEntity;
import java.util.HashSet;
import java.util.Set;

public class UserAuthoritiesDto {

  private String nameDto;
  private String emailDto;
  private RoleEntity roleDto;
  private Set<PermissionEntity> permissionsDto = new HashSet<>();

  public UserAuthoritiesDto(
          String nameDto, String emailDto,
          RoleEntity roleDto, Set<PermissionEntity> permissionsDto) {
    this.nameDto = nameDto;
    this.emailDto = emailDto;
    this.roleDto = roleDto;
    this.permissionsDto = permissionsDto;
  }


  // Getters and Setters
  public UserAuthoritiesDto() {
  }
  public String getNameDto() {
    return nameDto;
  }
  public void setNameDto(String nameDto) {
    this.nameDto = nameDto;
  }
  public String getEmailDto() {
    return emailDto;
  }
  public void setEmailDto(String emailDto) {
    this.emailDto = emailDto;
  }
  public RoleEntity getRoleDto() {
    return roleDto;
  }
  public void setRoleDto(RoleEntity roleDto) {
    this.roleDto = roleDto;
  }
  public Set<PermissionEntity> getPermissionsDto() {
    return permissionsDto;
  }
  public void setPermissionsDto(Set<PermissionEntity> permissionsDto) {
    this.permissionsDto = permissionsDto;
  }
}
