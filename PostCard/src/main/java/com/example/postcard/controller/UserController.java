package com.example.postcard.controller;

import com.example.postcard.config.auth.JwtUtils;
import com.example.postcard.dto.request.LoginDto;
import com.example.postcard.dto.request.SignUpDto;
import com.example.postcard.dto.responsedto.JwtResponse;
import com.example.postcard.dto.responsedto.MessageResponse;
import com.example.postcard.dto.responsedto.UserInfoResponseDTO;
import com.example.postcard.entity.ERole;
import com.example.postcard.entity.Role;
import com.example.postcard.entity.User;
import com.example.postcard.repository.RoleRepository;
import com.example.postcard.repository.UserRepository;
import com.example.postcard.service.UserService.UserDetailsImpl;
import com.example.postcard.service.UserService.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
   private AuthenticationManager authenticationManager;
  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder encoder;
  private JwtUtils jwtUtils;
  public UserController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder,
  JwtUtils jwtUtils){
      this.authenticationManager = authenticationManager;
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
      this.encoder = encoder;
      this.jwtUtils = jwtUtils;
  }

  @PostMapping("/register")
    public HttpEntity<?>Register(@Valid @RequestBody SignUpDto signUp){
      if (userRepository.existsByEmail(signUp.getEmail())){
          return ResponseEntity.badRequest().body(new MessageResponse("Error: Email already in use"));
      }
      User user = new User(signUp.getName(),
              signUp.getEmail(),
              encoder.encode(signUp.getPassword()));
      Set<String> stringRoles = signUp.getRole();
      Set<Role> roles = new HashSet<>();
      if (stringRoles == null) {
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
      } else {
          stringRoles.forEach(role -> {
              switch (role) {
                  case "admin":
                      Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                      roles.add(adminRole);

                      break;
                  case "mod":
                      Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                      roles.add(modRole);

                      break;
                  default:
                      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                      roles.add(userRole);
              }
          });
      }

      user.setRoles(roles);
      userRepository.save(user);

      return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

@PostMapping("/login")
  public HttpEntity<?> login(@Valid @RequestBody LoginDto loginDto){
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              loginDto.getEmail(),loginDto.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream()
              .map(item-> item.getAuthority())
              .collect(Collectors.toList());
      return ResponseEntity.ok(new JwtResponse(
              jwt,
              userDetails.getId(),
              userDetails.getEmail(),
              userDetails.getName(),
              roles
      ));
  }
}
