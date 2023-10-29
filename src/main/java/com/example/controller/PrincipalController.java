package com.example.controller;

import com.example.models.ERole;
import com.example.models.RoleEntity;
import com.example.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.controller.request.CreateUserDTO;
import com.example.models.UserEntity;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class PrincipalController {
	public UserRepository userRepository;
	@GetMapping("/hello")
	public String hello() {
		return "Hello world not secured";
	}
	
	@GetMapping("/helloSecured")
	public String helloSecured() {
		return "Hello world  secured";
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
		Set<RoleEntity> roles = createUserDTO.getRoles().stream().map(role -> RoleEntity.builder().name(ERole.valueOf(role)).build()).collect(Collectors.toSet());

		UserEntity userEntity;
		userEntity = UserEntity.builder()
				.username(createUserDTO.getUsername())
				.password(createUserDTO.getPassword())
				.email(createUserDTO.getEmail())
				.roles(roles)
				.build();

		userRepository.save(userEntity);
		return ResponseEntity.ok(userEntity);
	}
 @DeleteMapping ("/deleteUser")
	public String deleteUser(@RequestParam String id){
	 userRepository.deleteById(Long.parseLong(id));
		return "se ha borrado el user con el id".concat(id);
 }
}
