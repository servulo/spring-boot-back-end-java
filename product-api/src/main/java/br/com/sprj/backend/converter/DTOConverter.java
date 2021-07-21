package br.com.sprj.backend.converter;

import br.com.sprj.backend.dto.CategoryDTO;
import br.com.sprj.backend.dto.ProductDTO;
import br.com.sprj.backend.model.Category;
import br.com.sprj.backend.model.Product;

public class DTOConverter {

	public static CategoryDTO convert(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		return categoryDTO;
	}

	public static ProductDTO convert(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setProductIdentifier(product.getProductIdentifier());
		if (product.getCategory() != null) {
			productDTO.setCategory(DTOConverter.convert(product.getCategory()));
		}
		return productDTO;
	}

}
