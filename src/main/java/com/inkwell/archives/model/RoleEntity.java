package com.inkwell.archives.model;

import com.inkwell.archives.enums.RoleEnum;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity {

  public RoleEntity(RoleEnum roleEnum, Set<PermissionEntity> permissionList) {
    this.roleEnum = roleEnum;
    this.permissionList = permissionList;
  }
  public RoleEntity() {}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "roles_id")
  private int id;

  @Column(name = "roles_name")
  private RoleEnum roleEnum;

  // Bidirectional ManyToMany relationship to PermissionEntity
  @ManyToMany(
          fetch = FetchType.EAGER,
          cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(name = "roles_permissions",
          joinColumns = @JoinColumn(name = "role_id"),
          inverseJoinColumns = @JoinColumn(name = "permission_id"))
  private Set<PermissionEntity> permissionList = new HashSet<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public RoleEnum getRoleEnum() {
    return roleEnum;
  }

  public void setRoleEnum(RoleEnum roleEnum) {
    this.roleEnum = roleEnum;
  }

  public Set<PermissionEntity> getPermissionList() {
    return permissionList;
  }

  public void setPermissionList(Set<PermissionEntity> permissionList) {
    this.permissionList = permissionList;
  }

  @Override
  public String toString() {
    return "RoleEntity{" +
            "id=" + id +
            ", roleEnum=" + roleEnum +
            ", permissionList=" + permissionList +
            '}';
  }
}
