package com.inkwell.archives.service;

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

// Creation of user details service.
// File in charge of connecting to database and extract users/verify is user exists in database.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String useremail) {

    UserEntity userEntity = userRepository.findUserEntityByUseremail(useremail)
            .orElseThrow(() ->
                    new UsernameNotFoundException("The user " + useremail + " does not exist")
            );

    // List to store authorities user contains in its object/instantiation.
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    // Taking roles and converting them into SimpleGrantedAuthorities. This is the only way spring security is able to treat them.
    userEntity.getRoles().forEach(role -> authorityList
            .add(
                    new SimpleGrantedAuthority("ROLE_".concat(role.getRoleenum().name())))
    );

    // Taking permission from roles and updates in order to provide them to spring security
    userEntity.getRoles().stream()
            .flatMap(role -> role.getPermissions().stream())
            .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermission())));

    // This is the object must be returned. Looks hard to understand, but this is the manner spring security understands it.
    return new User(
            userEntity.getUseremail(),
            userEntity.getUserpassword(),
            userEntity.isEnabled(),
            userEntity.isAccountNoExpired(),
            userEntity.isCredentialNoExpired(),
            userEntity.isAccountNoLocked(),
            authorityList
    );
  }
}
