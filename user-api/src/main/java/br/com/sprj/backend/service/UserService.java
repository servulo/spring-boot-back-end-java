package br.com.sprj.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sprj.backend.dto.UserDTO;
import br.com.sprj.backend.model.User;
import br.com.sprj.backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> getAll() {
		List<User> users = userRepository.findAll();
		return users.stream().map(UserDTO::convert).collect(Collectors.toList());
	}

	public UserDTO findById(long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return UserDTO.convert(user.get());
		}
		return null;
	}

	public UserDTO save(UserDTO userDTO) {
		User user = userRepository.save(User.convert(userDTO));
		return UserDTO.convert(user);
	}

	public UserDTO delete(long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			userRepository.delete(user.get());
		}
		return null;
	}

	public UserDTO findBySocialSecurity(String socialSecurity) {
		User user = userRepository.findBySocialSecurity(socialSecurity);
		if (user != null) {
			return UserDTO.convert(user);
		}
		return null;
	}

	public List<UserDTO> queryByName(String name) {
		List<User> users = userRepository.queryByNameLike(name);
		return users.stream().map(UserDTO::convert).collect(Collectors.toList());
	}

}