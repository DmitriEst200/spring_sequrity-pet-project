package org.spring.social.testdrive.controller;

import org.spring.social.testdrive.controller.dto.LoginDto;
import org.spring.social.testdrive.security.JwtTokenProvider;
import org.spring.social.testdrive.security.dto.ConcreteUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.spring.social.testdrive.GlobalData.API_URL;

@RestController
@RequestMapping(API_URL + "/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        String jwt = tokenProvider.generateToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ConcreteUserDetails userPrincipal = (ConcreteUserDetails) authentication.getPrincipal();
        userPrincipal.setJwt(jwt);
        return ResponseEntity.ok(userPrincipal);
    }

    @PostMapping("/Google")
    public ResponseEntity<?> authenticateUserOverGoogle(@Valid @RequestBody LoginDto loginRequest) {
        return null;
    }

    @PostMapping("/Facebook")
    public ResponseEntity<?> authenticateUserOverFacebook(@Valid @RequestBody LoginDto loginRequest) {
        return null;
    }
}
