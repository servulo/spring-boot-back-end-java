package br.com.sprj.backend.converter;

import java.util.stream.Collectors;

import br.com.sprj.backend.dto.ItemDTO;
import br.com.sprj.backend.dto.ShopDTO;
import br.com.sprj.backend.model.Item;
import br.com.sprj.backend.model.Shop;

public class DTOConverter {

	public static ItemDTO convert(Item item) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setProductIdentifier(item.getProductIdentifier());
		itemDTO.setPrice(item.getPrice());
		return itemDTO;
	}

	public static ShopDTO convert(Shop shop) {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setUserIdentifier(shop.getUserIdentifier());
		shopDTO.setTotal(shop.getTotal());
		shopDTO.setDate(shop.getDate());
		shopDTO.setItems(shop.getItems().stream().map(DTOConverter::convert).collect(Collectors.toList()));
		return shopDTO;
	}

}