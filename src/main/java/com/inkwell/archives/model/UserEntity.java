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
  private String username;

  @Column(name = "user_email", unique = true)
  private String useremail;

  @Column(name = "user_password")
  private String userpassword;

  @Column(name = "user_age")
  private String userage;

  @Column(name = "user_country")
  private String usercountry;

  @Column(name = "user_city")
  private String usercity;

  @Column(name = "user_address")
  private String useraddress;

  @Column(name = "user_role_fk")
  private RoleEntity userrolefk;

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
