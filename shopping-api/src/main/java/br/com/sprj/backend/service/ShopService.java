package br.com.sprj.backend.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sprj.backend.converter.DTOConverter;
import br.com.sprj.backend.dto.ItemDTO;
import br.com.sprj.backend.dto.ProductDTO;
import br.com.sprj.backend.dto.ShopDTO;
import br.com.sprj.backend.model.Shop;
import br.com.sprj.backend.repository.ShopRepository;

@Service
public class ShopService {

	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;

	public List<ShopDTO> getAll() {
		List<Shop> shops = shopRepository.findAll();
		return shops
				.stream()
				.map(DTOConverter::convert)
				.collect(Collectors.toList());
	}

	public List<ShopDTO> getByUser(String userIdentifier) {
		List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
		return shops
				.stream()
				.map(DTOConverter::convert)
				.collect(Collectors.toList());
	}

	public List<ShopDTO> getByDate(ShopDTO shopDTO) {
		List<Shop> shops = shopRepository.findAllByDateGreaterThanEqual(shopDTO.getDate());
		return shops
				.stream()
				.map(DTOConverter::convert)
				.collect(Collectors.toList());

	}

	public ShopDTO findById(long productID) {
		Optional<Shop> shop = shopRepository.findById(productID);
		if (shop.isPresent()) {
			return DTOConverter.convert(shop.get());
		}
		return null;
	}
	
	public ShopDTO save(ShopDTO shopDTO) {
		if(userService.getUserBySocialSecurity(shopDTO.getUserIdentifier()) == null) {
			return null;
		}
		if(!validateProducts(shopDTO.getItems())) {
			return null;
		}
		shopDTO.setTotal(shopDTO.getItems()
				.stream()
				.map(x -> x.getPrice())
				.reduce((float) 0, Float::sum));
		Shop shop = Shop.convert(shopDTO);
		shop.setDate(new Date());
		shop = shopRepository.save(shop);
		return DTOConverter.convert(shop);
	}

	private boolean validateProducts(List<ItemDTO> items) {
		for(ItemDTO item: items) {
			ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());
			if(productDTO == null) {
				return false;
			}
			item.setPrice(productDTO.getPrice());
		}
		return true;
	}
	
}