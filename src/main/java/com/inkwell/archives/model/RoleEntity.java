package com.inkwell.archives.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "roles_id")
  private int id;

  @Column(name = "roles_name")
  private String rolesname;

  // Unidirectional relationship
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "roles_permissions_fk")
  private PermissionEntity permissions;

}
