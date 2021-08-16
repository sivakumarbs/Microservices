package com.app.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.User;
import com.app.entity.UserRequest;
import com.app.entity.UserResponse;
import com.app.service.IUserService;
import com.app.util.JwtUtil;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IUserService service;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("signup")
	public ResponseEntity<String> userSingUp(@RequestBody User user) {
		service.saveUser(user);
		return ResponseEntity.ok("SignUp succesfully completed");
	}

	@PostMapping("/login")
	public UserResponse loginUser(@RequestBody UserRequest userRequest)
	{

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userRequest.getUsername(), 
						userRequest.getPassword()
						)
				);

		String token=jwtUtil.generateToken(userRequest.getUsername());
		return new UserResponse("Success",token,service.findByUsername(userRequest.getUsername()).getRoles(),HttpStatus.OK);
	}

	@PostMapping("/validate")
	public ResponseEntity<?> accessUserData(Principal p) {
		ResponseEntity<?> response = null;
		try {
			if(p.getName() != null) {
				response =  new ResponseEntity<String>("It is valid token",HttpStatus.OK);
			}
		} catch (Exception e) {

			response =  new ResponseEntity<String>("Not valid token",HttpStatus.UNAUTHORIZED);	
		}
		return response;
	}
	////
}
