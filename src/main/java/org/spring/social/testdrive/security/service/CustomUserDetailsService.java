package org.spring.social.testdrive.security.service;

import org.spring.social.testdrive.security.dto.ConcreteUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.spring.social.testdrive.security.dto.ConcreteUserDetails.ConcreteUserDetailsBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   public UserDetails loadUserById(Long userId){
       return getBuilderWithDefaultParams()
           .id(userId)
           .build();
   }

   public UserDetails loadUserByUsername(String userLogin){
       return getBuilderWithDefaultParams()
               .username(userLogin)
               .build();
   }

   private final ConcreteUserDetailsBuilder getBuilderWithDefaultParams(){

       final List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();
       autorities.add(new SimpleGrantedAuthority("USER"));

       return ConcreteUserDetails
               .builder()
               .accountNonExpired(true)
               .accountNonLocked(true)
               .credentialsNonExpired(true)
               .enabled(true)
               .authorities(autorities);
   }
}
