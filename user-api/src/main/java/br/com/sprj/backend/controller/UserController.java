package br.com.sprj.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sprj.backend.dto.UserDTO;
import br.com.sprj.backend.exception.UserNotFoundException;
import br.com.sprj.backend.service.UserService;

@RestController
public class UserController {

	public static List<UserDTO> users = new ArrayList<UserDTO>();

	@Autowired
	private UserService userService;

	@GetMapping("/user/")
	public List<UserDTO> getUsers() {
		List<UserDTO> users = userService.getAll();
		return users;
	}

	@GetMapping("/user/{id}")
	UserDTO findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@PostMapping("/user")
	UserDTO newUser(@RequestBody UserDTO userDTO) {
		return userService.save(userDTO);
	}

	@GetMapping("/user/ss/{socialSecurity}")
	UserDTO findBySocialSecurity(@PathVariable String socialSecurity) {
		return userService.findBySocialSecurity(socialSecurity);
	}

	@DeleteMapping("/user/{id}")
	UserDTO delete(@PathVariable Long id) throws UserNotFoundException {
		return userService.delete(id);
	}

	@GetMapping("/user/search")
	public List<UserDTO> queryByName(
			@RequestParam(name="name", required = true) String name) {
	    return userService.queryByName(name);
	}

}