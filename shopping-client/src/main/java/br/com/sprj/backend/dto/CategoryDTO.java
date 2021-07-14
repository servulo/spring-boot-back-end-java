package br.com.sprj.backend.dto;

import javax.validation.constraints.NotNull;

public class CategoryDTO {

	@NotNull
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
