package br.com.sprj.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.sprj.backend.dto.UserDTO;

@Service
public class UserService {

	public UserDTO getUserBySocialSecurity(String socialSecurity) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/ss/" + socialSecurity;
		ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
		return response.getBody();
	}

}