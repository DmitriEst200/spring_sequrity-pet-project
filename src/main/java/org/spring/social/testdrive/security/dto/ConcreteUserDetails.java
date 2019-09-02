package org.spring.social.testdrive.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class ConcreteUserDetails implements UserDetails {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    @JsonIgnore
    private String password;
    @Getter
    @Setter
    private boolean accountNonExpired;
    @Getter
    @Setter
    private boolean accountNonLocked;
    @Getter
    @Setter
    private boolean credentialsNonExpired;
    @Getter
    @Setter
    private boolean enabled;
    @Getter
    @Setter
    private Collection<? extends GrantedAuthority> authorities;
    @Getter
    @Setter
    private String jwt;

    @Builder
    public ConcreteUserDetails(Long id, String username, String password, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled,
                          Collection<? extends GrantedAuthority> authorities, String jwt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = authorities;
        this.jwt = jwt;
    }
}
