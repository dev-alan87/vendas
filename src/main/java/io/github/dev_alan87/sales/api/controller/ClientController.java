package io.github.dev_alan87.sales.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.dev_alan87.sales.domain.entity.Client;

@Controller
@RequestMapping("/api/clients")
public class ClientController {

	@RequestMapping(
			value = { "/hello/{name}", "/welcome" }, 
			method = RequestMethod.POST,
			consumes = { "application/json", "application/xml" },
			produces = { "application/json", "application/xml" }
			)
	@ResponseBody
	public String hellloClient(@PathVariable("name") String clientName, @RequestBody Client client) {
		return String.format("Hello, %s. Welcome!", clientName);
	}
	
	
	
}