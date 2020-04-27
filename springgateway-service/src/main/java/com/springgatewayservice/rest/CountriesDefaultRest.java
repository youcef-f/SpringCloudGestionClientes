package com.springgatewayservice.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

public class CountriesDefaultRest {

	// defaultcountries
	@GetMapping("/defaultsalatmuslim")
	public Map<String, String> defaultcountries() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("message", "default countries");
		data.put("countries", "Maroc , Algérie, Tunisie ...");
		return data;
	}

	// defaultsalatmuslim
	@GetMapping("/defaultsalatmuslim")
	public Map<String, String> defaultsalatmuslim() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("message", "default salat ..");
		data.put("soubr", "5:00");
		data.put("thour", "13:00");
		data.put("asr", "16:00");
		data.put("maghreb", "20:30");
		data.put("isha", "22:00");
		return data;
	}
}
