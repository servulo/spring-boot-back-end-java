package br.com.sprj.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.sprj.backend.dto.ProductDTO;
import br.com.sprj.backend.exception.ProductNotFoundException;

@Service
public class ProductService {

	public ProductDTO getProductByIdentifier(String productIdentifier) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8081/product/" + productIdentifier;
			System.out.println(url);
			ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);
			return response.getBody();			
		} catch (HttpClientErrorException.NotFound e) {
			throw new ProductNotFoundException();
		}
	}

}