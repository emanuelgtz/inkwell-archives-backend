package com.inkwell.archives.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "permissions")
public class PermissionEntity {
  @Builder
  public PermissionEntity(String permission) {
    this.permission = permission;
  }
  public PermissionEntity() {}
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "permissions_id")
  private int id;

  @Column(name = "permission")
  private String permission;

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

  @Override
  public String toString() {
    return "PermissionEntity{" +
            "id=" + id +
            ", permission='" + permission + '\'' +
            '}';
  }
}

