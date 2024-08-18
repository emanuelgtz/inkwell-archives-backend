package com.inkwell.archives.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class PermissionEntity {

  public PermissionEntity(String permission, Set<RoleEntity> rolesList) {
    this.permission = permission;
    this.rolesList = rolesList;
  }

  public PermissionEntity() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "permissions_id")
  private int id;

  @Column(name = "permission")
  private String permission;

  // Bidirectional ManyToMany relationship to RoleEntity
  @ManyToMany(
          fetch = FetchType.EAGER,
          cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(name = "roles_permissions", joinColumns = @JoinColumn(name = "permission_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleEntity> rolesList = new HashSet<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  public Set<RoleEntity> getRolesList() {
    return rolesList;
  }

  public void setRolesList(Set<RoleEntity> rolesList) {
    this.rolesList = rolesList;
  }

  @Override
  public String toString() {
    return "PermissionEntity{" +
            "id=" + id +
            ", permission='" + permission + '\'' +
            ", rolesList=" + rolesList +
            '}';
  }
}

