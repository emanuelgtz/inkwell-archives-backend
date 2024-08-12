package com.inkwell.archives.model;

import com.inkwell.archives.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
import java.util.Set;

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
  private RoleEnum roleenum;

  // Unidirectional relationship
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "roles_permissions_fk")
  private Set<PermissionEntity> permissions = new HashSet<>();

}
