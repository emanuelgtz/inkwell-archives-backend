package com.inkwell.archives.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private int id;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "user_email", unique = true)
  private String userEmail;

  @Column(name = "user_password")
  private String userPassword;

  @Column(name = "user_age")
  private String userAge;

  @Column(name = "user_country")
  private String userCountry;

  @Column(name = "user_city")
  private String userCity;

  @Column(name = "user_address")
  private String userAddress;

  @Column(name = "user_role_fk")
  private RoleEntity userRolefk;

  // user details properties
  // These are mandatory to be implemented
  @Column(name = "is_enabled")
  private boolean isEnabled;

  @Column(name = "account_No_Expired")
  private boolean accountNoExpired;

  @Column(name = "account_No_Locked")
  private boolean accountNoLocked;

  @Column(name = "credential_No_Expired")
  private boolean credentialNoExpired;

  // Unidirectional relationship
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_role_fk", nullable = false)
  private Set<RoleEntity> roles = new HashSet<>();
}
