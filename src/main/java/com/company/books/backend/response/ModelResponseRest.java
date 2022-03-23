package com.company.books.backend.response;

public class ModelResponseRest<T> extends ResponseRest{

	private ModelResponse<T> modelResponse = new ModelResponse<T>();

	public ModelResponse<T> getModelResponse() {
		return modelResponse;
	}

	public void setModelResponse(ModelResponse<T> modelResponse) {
		this.modelResponse = modelResponse;
	}
	
}
