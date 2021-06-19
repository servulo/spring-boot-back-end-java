package br.com.sprj.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.sprj.backend.model.Product;

public class ProductDTO {

	@NotBlank
	private String productIdentifier;
	@NotBlank
	private String name;
	@NotNull
	private Float price;
	@NotBlank
	private String description;
	@NotNull
	private CategoryDTO category;

	public String getProductIdentifier() {
		return productIdentifier;
	}

	public void setProductIdentifier(String productIdentifier) {
		this.productIdentifier = productIdentifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public static ProductDTO convert(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setProductIdentifier(product.getProductIdentifier());
		productDTO.setDescription(product.getDescription());
		if (product.getCategory() != null) {
			productDTO.setCategory(CategoryDTO.convert(product.getCategory()));
		}
		return productDTO;
	}

}