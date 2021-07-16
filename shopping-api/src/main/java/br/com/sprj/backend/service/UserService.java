package br.com.sprj.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.sprj.backend.dto.UserDTO;
import br.com.sprj.backend.exception.UserNotFoundException;

@Service
public class UserService {

	public UserDTO getUserBySocialSecurity(String socialSecurity) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8080/user/ss/" + socialSecurity;
			ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
			return response.getBody();

		} catch (HttpClientErrorException.NotFound e) {
			throw new UserNotFoundException();
		}
	}

}