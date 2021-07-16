package br.com.sprj.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sprj.backend.converter.DTOConverter;
import br.com.sprj.backend.dto.UserDTO;
import br.com.sprj.backend.exception.UserNotFoundException;
import br.com.sprj.backend.model.User;
import br.com.sprj.backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> getAll() {
		List<User> users = userRepository.findAll();
		return users
				.stream()
				.map(DTOConverter::convert)
				.collect(Collectors.toList());
	}

	public UserDTO findById(long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return DTOConverter.convert(user.get());
		}
		return null;
	}
	
	public UserDTO save(UserDTO userDTO) {
		userDTO.setKey(UUID.randomUUID().toString());
		User user = userRepository.save(User.convert(userDTO));
		return DTOConverter.convert(user);
	}

	public UserDTO delete(long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			userRepository.delete(user.get());
		}
		return null;
	}

	public UserDTO findBySocialSecurity(String socialSecurity, String key) {
		User user = userRepository.findBySocialSecurityAndKey(socialSecurity, key);
		if (user != null) {
			return DTOConverter.convert(user);
		}
		throw new UserNotFoundException();
	}

	public List<UserDTO> queryByName(String name) {
		List<User> users = userRepository.queryByNameLike(name);
		return users
				.stream()
				.map(DTOConverter::convert)
				.collect(Collectors.toList());
	}

}