package com.test.test.template;

import org.springframework.stereotype.Component;

/**
 * response factory
 * @author jay
 *
 */
@Component
public class ResponseFactory {

	public <S> ResponseTemplate<S> genResponse(S response) {
		return new ResponseTemplate<S>(response);
	}
}
