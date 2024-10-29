package com.inkwell.archives.model;

import com.inkwell.archives.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
  public UserEntity(int id,
                    String userName, String userEmail,
                    String userPassword, int userAge, String userCountry,
                    String userCity, String userAddress, List<RoleEntity> role) {
    this.id = id;
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
  @Column(name = "user_email", unique = true, nullable = false)
  private String userEmail;
  @Column(name = "user_password")
  private String userPassword;
  @Column(name = "user_age")
  private int userAge;
  @Column(name = "user_country")
  private String userCountry;
  @Column(name = "user_city")
  private String userCity;
  @Column(name = "user_address")
  private String userAddress;
  // Unidirectional relationship
  @ManyToMany
  @JoinTable(
          name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id_fk"),
          inverseJoinColumns = @JoinColumn(name = "roles_id_fk")
  )
  private List<RoleEntity> role = new ArrayList<>();

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

  public int getUserAge() {
    return userAge;
  }

  public void setUserAge(int userAge) {
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

  public List<RoleEntity> getRole() {
    return role;
  }

  public void setRole(List<RoleEntity> role) {
    this.role = role;
  }



  // UserDetails methods
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //return List.of(new SimpleGrantedAuthority(role.getRoleName().toString()));
    // Validation
    if(role == null || role.isEmpty()) {return List.of();}

    return role.stream()
            .map(RoleEntity::getRoleName)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
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


  @Override
  public String toString() {
    return "UserEntity{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", userEmail='" + userEmail + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userAge=" + userAge +
            ", userCountry='" + userCountry + '\'' +
            ", userCity='" + userCity + '\'' +
            ", userAddress='" + userAddress + '\'' +
            ", role=" + role +
            '}';
  }
}
