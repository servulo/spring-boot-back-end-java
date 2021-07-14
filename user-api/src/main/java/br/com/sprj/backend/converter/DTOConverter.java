package br.com.sprj.backend.converter;

import br.com.sprj.backend.dto.UserDTO;
import br.com.sprj.backend.model.User;

public class DTOConverter {

	public static UserDTO convert(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(user.getName());
		userDTO.setAddress(user.getAddress());
		userDTO.setSocialSecurity(user.getSocialSecurity());
		return userDTO;
	}

}
