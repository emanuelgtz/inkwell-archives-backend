package com.inkwell.archives.service;

import com.inkwell.archives.model.RoleEntity;
import com.inkwell.archives.model.UserEntity;
import com.inkwell.archives.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    UserEntity userEntity = userRepository.findByUserEmail(userEmail)
            .orElseThrow(() ->
                    new UsernameNotFoundException("The user " + userEmail + " does not exist")
            );

    // List to store authorities user contains in its object/instantiation.
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    // Taking roles and converting them into SimpleGrantedAuthorities. This is the only way spring security is able to treat them.
    RoleEntity role = userEntity.getRole();
    if(role != null) {
      authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())));

      authorityList.add(new SimpleGrantedAuthority(role.getPermissionList().getPermission()));

    }

    /*userEntity.getRole().forEach(role -> authorityList
            .add(
                    new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())))
    );*/

    // Taking permission from roles and updates in order to provide them to spring security
    /*userEntity.getRole().stream()
            .map(role -> role.getPermissions())
            .filter(Objects::nonNull)
            .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermission())));*/


    return new User(
            userEntity.getUserEmail(),
            userEntity.getUserPassword(),
            userEntity.isEnabled(),
            userEntity.isAccountNoExpired(),
            userEntity.isCredentialNoExpired(),
            userEntity.isAccountNoLocked(),
            authorityList
    );
  }
}
