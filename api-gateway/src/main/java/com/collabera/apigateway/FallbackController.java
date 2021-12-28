package com.collabera.apigateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

	
	  @RequestMapping("/adminFallback") 
	  public Mono<String> adminServiceFallback(){ 
		  return Mono.just("Admin Service is taking too long to respond or is down. Pls try after some time!!");
	  }
	  
	 /* @RequestMapping("/customerFallback") public Mono<String>
	 * customerServiceFallback() { return Mono.
	 * just("Customer Service is taking too long to respond or is down. Pls try after some time!!"
	 * ); }
	 * 
	 * @RequestMapping("/orderFallback") public Mono<String> orderServiceFallback()
	 * { return Mono.
	 * just("Order Service is taking too long to respond or is down. Pls try after some time!!"
	 * ); }
	 */
}
