package com.inkwell.archives.service;

import com.inkwell.archives.enums.PermissionEnum;
import com.inkwell.archives.enums.RoleEnum;
import com.inkwell.archives.model.PermissionEntity;
import com.inkwell.archives.model.RoleEntity;
import com.inkwell.archives.model.UserEntity;
import com.inkwell.archives.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Creation of user details service.
// File in charge of connecting to database and extract users/verify is user exists in database.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String userEmail) {

    // List to store authorities user contains on its object/instantiation.
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    UserEntity userEntity = userRepository.findByUserEmail(userEmail).orElseThrow(() ->
            new UsernameNotFoundException("The user " + userEmail + " does not exist")
    );



    if(userEntity.getRole() != null) {
      for(RoleEntity role : userEntity.getRole()) {
        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleName().toString())));

        // Validation filter to permissions
        if(role.getPermissionList() != null) {
          for(PermissionEntity permission : role.getPermissionList()) {
            authorityList.add(new SimpleGrantedAuthority(permission.getPermission().toString()));
          }
        }

      }
    }

    return new User(
            userEntity.getUserEmail(),
            userEntity.getUserPassword(),
            userEntity.isEnabled(),
            userEntity.isAccountNonExpired(),
            userEntity.isCredentialsNonExpired(),
            userEntity.isAccountNonLocked(),
            authorityList
    );
  }
}
