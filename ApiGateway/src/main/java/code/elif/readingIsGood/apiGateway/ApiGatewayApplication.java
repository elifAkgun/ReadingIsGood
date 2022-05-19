package code.elif.readingIsGood.apiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder rlb, AuthorizationHeaderFilter
			authorizationHeaderFilter) {

		return rlb
				.routes()
				.route(p -> p
						.path("/customer-ws/customers/*")
						.filters(f -> f.removeRequestHeader("Cookie")
								.rewritePath("/customer-ws/(?<segment>.*)", "/$\\{segment}")
								.filter(authorizationHeaderFilter.apply(new
										AuthorizationHeaderFilter.Config())))
						.uri("lb://customer-ws")
				)
				.build();
	}
}
