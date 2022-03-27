package com.company.books.backend.response;

import java.util.List;

public class ModelResponse<T> {

	List<T> model;

	public List<T> getModel() {
		return model;
	}

	public void setModel(List<T> model) {
		this.model = model;
	}
	
}
