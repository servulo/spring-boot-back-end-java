package br.com.sprj.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sprj.backend.dto.ShopDTO;
import br.com.sprj.backend.service.ShopService;

@RestController
@RequestMapping("/shopping")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@GetMapping
	public List<ShopDTO> getShops() {
		List<ShopDTO> products = shopService.getAll();
		return products;
	}

	@GetMapping("/shopByUser/{userIdentifier}")
	public List<ShopDTO> getShops(@PathVariable String userIdentifier) {
		List<ShopDTO> products = shopService.getByUser(userIdentifier);
		return products;
	}

	@GetMapping("/shopByDate")
	public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
		List<ShopDTO> products = shopService.getByDate(shopDTO);
		return products;
	}

	@GetMapping("/{id}")
	public ShopDTO findById(@PathVariable Long id) {
		return shopService.findById(id);
	}

	@PostMapping
	public ShopDTO newShop(@RequestHeader(name = "key", required = true) String key, @RequestBody ShopDTO shopDTO) {
		return shopService.save(shopDTO, key);
	}

}