package com.netflix.zuul.sample.filters.endpoint

import com.netflix.zuul.filters.http.HttpSyncEndpoint
import com.netflix.zuul.message.http.HttpRequestMessage
import com.netflix.zuul.message.http.HttpResponseMessage
import com.netflix.zuul.message.http.HttpResponseMessageImpl
import com.netflix.zuul.stats.status.StatusCategoryUtils
import com.netflix.zuul.stats.status.ZuulStatusCategory

class Unauthorized extends HttpSyncEndpoint {

    @Override
    HttpResponseMessage apply(HttpRequestMessage request) {
		System.out.println("hit on the Unauthorized endpoint")
		
        HttpResponseMessage resp = new HttpResponseMessageImpl(request.getContext(), request, 401)
        resp.setBodyAsText("Not authorized")
//
		resp.finishBufferedBodyIfIncomplete()

        return resp
    }
	
}
