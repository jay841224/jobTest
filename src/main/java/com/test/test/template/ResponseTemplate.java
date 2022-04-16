package com.test.test.template;

import lombok.Data;

@Data
public class ResponseTemplate<S> {

	private Header header;

	private S reponse;

	public ResponseTemplate() {

	}

	public ResponseTemplate(S response) {
		this.reponse = response;
		this.header = new Header();
	}

	public ResponseTemplate(S response, Header header) {
		this.reponse = response;
		this.header = header;
	}
}
