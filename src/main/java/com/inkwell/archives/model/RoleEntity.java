package com.inkwell.archives.model;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity {
  @Builder
  public RoleEntity(
          String roleName,
          List<PermissionEntity> permissionList) {
    this.roleName = roleName;
    this.permissionList = permissionList;
  }

  public RoleEntity() {}
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "roles_id")
  private int id;

  @Column(name = "roles_name")
  private String roleName;

  // Bidirectional ManyToMany relationship to PermissionEntity
  @ManyToMany
  @JoinTable(name = "roles_permissions",
          joinColumns = @JoinColumn(name = "role_id"),
          inverseJoinColumns = @JoinColumn(name = "permission_id"))
  private List<PermissionEntity> permissionList = new ArrayList<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public List<PermissionEntity> getPermissionList() {
    return permissionList;
  }

  public void setPermissionList(List<PermissionEntity> permissionList) {
    this.permissionList = permissionList;
  }

  @Override
  public String toString() {
    return "RoleEntity{" +
            "id=" + id +
            ", roleName='" + roleName + '\'' +
            ", permissionList=" + permissionList +
            '}';
  }
}
