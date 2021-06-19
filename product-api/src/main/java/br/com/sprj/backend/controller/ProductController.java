package br.com.sprj.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sprj.backend.dto.ProductDTO;
import br.com.sprj.backend.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public List<ProductDTO> getProducts() {
		List<ProductDTO> products = productService.getAll();
		return products;
	}

	@GetMapping("/product/category/{categoryId}")
	public List<ProductDTO> getProductByCategory(@PathVariable long categoryId) {
		List<ProductDTO> products = productService.getProductByCategoryId(categoryId);
		return products;
	}

	@GetMapping("/product/{productIdentifier}")
	public ProductDTO findById(@PathVariable String productIdentifier) {
		return productService.findByProductIdentifier(productIdentifier);
	}

	@PostMapping("/product")
	public ProductDTO newProduct(@Valid @RequestBody ProductDTO productDTO) {
		return productService.save(productDTO);
	}

	@DeleteMapping("/product/{id}")
	public ProductDTO delete(@PathVariable long id) throws ProductNotFoundException {
		return productService.delete(id);
	}

}
