package com.springgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableHystrix
@SpringBootApplication
public class SpringgatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringgatewayServiceApplication.class, args);
	}

	// DEFINITION DE ROUTES STATIQUES
	@Bean
	RouteLocator getwayRoutes(RouteLocatorBuilder builder) {
		return builder.routes() //
				.route(r -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE").id("r1")) // http://localhost:8888/customers
				.route(r -> r.path("/products/**").uri("lb://INVENTORY-SERVICE").id("r2")) // http://localhost:8888/products
				.route(r -> r.path("/productitems/**").uri("lb://BILLING-SERVICE").id("r3")) // http://localhost:8888/productitems
				.route(r -> r.path("/billings/**").uri("lb://BILLING-SERVICE").id("r4")) // http://localhost:8888/billings
				
				.route(r -> r // API COUNTRY RAPIDAPI   https://rapidapi.com/muslim/api/muslim-salat
						.path("/publiccountries/**") //
						.filters(f -> f 
								.hystrix(h-> h.setName("hystrix-countries").setFallbackUri("forward:/defaultcountries"))
								.addRequestHeader("x-rapidapi-host", "restcountries-v1.p.rapidapi.com") //
								.addRequestHeader("x-rapidapi-key",
										"27e8d529xxxxxx(voir mail pour key)xxxxde287f1f") //
								.rewritePath("/publiccountries/(?<segment>.*)", "/${segment}")) // enleve"publiccountires/xxxxx" par "/xxxxx" //

						
						.uri("https://restcountries-v1.p.rapidapi.com/") //
						.id("r5")) // https://restcountries-v1.p.rapidapi.com/all
				.route(r -> r  // API MUSLIM SALAT	 RAPIDAPI  https://rapidapi.com/apilayernet/api/rest-countries-v1
						.path("/publicsalatmuslim/**") //
						.filters(f -> f //
								.hystrix(h-> h.setName("hystrix-salatmuslim").setFallbackUri("forward:/defaultsalatmuslim"))
								.addRequestHeader("x-rapidapi-host", "muslimsalat.p.rapidapi.com") //
								.addRequestHeader("x-rapidapi-key",
										"27e8d529xxxxxx(voir mail pour key)xxxxde287f1f") //
								.rewritePath("/publicsalatmuslim/(?<segment>.*)", "/${segment}")) // enleve"publiccountires/xxxxx" par "/xxxxx"
						.uri("https://muslimsalat.p.rapidapi.com/") //
						.id("r5")) // https://restcountries-v1.p.rapidapi.com/all
				
				.build();
	}


	
	// DEFINITION ROUTES DYNAMIQUE
	// http://localhost:8888/CUSTOMER-SERVICE/customers
	// http://localhost:8888/INVENTORY-SERVICE/products
	// http://localhost:8888/BILLING-SERVICE/billings
	// http://localhost:8888/BILLING-SERVICE/productitems
	@Bean
	DiscoveryClientRouteDefinitionLocator clientRouteDefinitionLocator(ReactiveDiscoveryClient reactiveDiscoveryClient,
			DiscoveryLocatorProperties discoveryLocatorProperties) {
		return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient, discoveryLocatorProperties);
	}
}
