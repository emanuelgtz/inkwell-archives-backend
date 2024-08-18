package com.inkwell.archives.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

  public UserEntity(String userName, String userEmail, String userPassword, String userAge, String userCountry, String userCity, String userAddress, RoleEntity role) {
    this.userName = userName;
    this.userEmail = userEmail;
    this.userPassword = userPassword;
    this.userAge = userAge;
    this.userCountry = userCountry;
    this.userCity = userCity;
    this.userAddress = userAddress;
    this.role = role;
  }

  public UserEntity() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private int id;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "user_email")
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

  // Unidirectional relationship
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "user_role_fk", referencedColumnName = "roles_id", nullable = false)
  private RoleEntity role;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserAge() {
    return userAge;
  }

  public void setUserAge(String userAge) {
    this.userAge = userAge;
  }

  public String getUserCountry() {
    return userCountry;
  }

  public void setUserCountry(String userCountry) {
    this.userCountry = userCountry;
  }

  public String getUserCity() {
    return userCity;
  }

  public void setUserCity(String userCity) {
    this.userCity = userCity;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  public RoleEntity getRole() {
    return role;
  }

  public void setRole(RoleEntity role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", userEmail='" + userEmail + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userAge='" + userAge + '\'' +
            ", userCountry='" + userCountry + '\'' +
            ", userCity='" + userCity + '\'' +
            ", userAddress='" + userAddress + '\'' +
            ", role=" + role +
            '}';
  }

  // UserDetails methods
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.getRoleEnum().name()));
  }

  @Override
  public String getPassword() {
    return userPassword;
  }

  @Override
  public String getUsername() {
    return userEmail;
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

}
