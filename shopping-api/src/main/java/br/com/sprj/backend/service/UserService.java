package br.com.sprj.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sprj.backend.dto.UserDTO;
import br.com.sprj.backend.exception.UserNotFoundException;

@Service
public class UserService {

	public UserDTO getUserBySocialSecurity(String socialSecurity, String key) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/user/ss/" + socialSecurity);
			uriComponentsBuilder.queryParam("key", key);
			ResponseEntity<UserDTO> response = restTemplate.getForEntity(uriComponentsBuilder.toUriString(),
					UserDTO.class);
			return response.getBody();
		} catch (HttpClientErrorException.NotFound e) {
			throw new UserNotFoundException();
		}
	}

}