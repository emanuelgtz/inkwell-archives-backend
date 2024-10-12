package com.inkwell.archives.model;

import com.inkwell.archives.enums.PermissionEnum;
import com.inkwell.archives.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class PermissionEntity {
  public PermissionEntity(PermissionEnum permission, Set<RoleEntity> rolesList) {
    this.permission = permission;
    this.rolesList = rolesList;
  }
  public PermissionEntity() {}
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "permissions_id")
  private int id;

  @Column(name = "permission")
  @Enumerated(EnumType.STRING)
  private PermissionEnum permission;

  // Bidirectional ManyToMany relationship to RoleEntity
  @ManyToMany(
          fetch = FetchType.EAGER,
          cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(name = "roles_permissions",
          joinColumns = @JoinColumn(name = "permission_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleEntity> rolesList = new HashSet<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public PermissionEnum getPermission() {
    return permission;
  }

  public void setPermission(PermissionEnum permission) {
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

