package com.inkwell.archives.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity {
  public RoleEntity(String roleName,
                    Set<PermissionEntity> permissionList) {
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


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
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
            ", roleEnum=" + roleName +
            ", permissionList=" + permissionList +
            '}';
  }
}
