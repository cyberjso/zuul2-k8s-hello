/*
 * Copyright 2018 Netflix, Inc.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package com.netflix.zuul.sample.filters.inbound

import com.netflix.zuul.context.SessionContext
import com.netflix.zuul.filters.http.HttpInboundSyncFilter
import com.netflix.zuul.message.Headers;
import com.netflix.zuul.message.http.HttpRequestMessage
import com.netflix.zuul.sample.filters.endpoint.Unauthorized

class AuthFilter extends HttpInboundSyncFilter {

    @Override
    int filterOrder() {
        return 1
    }

    @Override
    boolean shouldFilter(HttpRequestMessage httpRequestMessage) {
        return true
    }

    @Override
    HttpRequestMessage apply(HttpRequestMessage request) {
		System.out.println("validating auth token");
		
		SessionContext context = request.getContext()
		Headers headers = request.getHeaders();
		
		String token = headers.getFirst("Authorization")
		
//		// some other token invalidation logic would goes in here
		if (token == null) {			
			request.getContext().setEndpoint(Unauthorized.class.getCanonicalName())
		}
		
        return request
    }
		
}
