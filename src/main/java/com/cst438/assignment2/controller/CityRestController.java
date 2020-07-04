package com.cst438.assignment2.controller;

import com.cst438.assignment2.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.assignment2.domain.*;
import com.cst438.assignment2.service.CityService;

import java.util.List;

@RestController
public class CityRestController {
	
	@Autowired
	private CityService cityService;

	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/api/cities/{city}")
	public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {

		CityInfo cityInfo = cityService.getCityInfo(cityName);

		if (cityInfo == null) {
			return new ResponseEntity<CityInfo>(HttpStatus.NOT_FOUND);
		} else {
			TempAndTime weather = weatherService.getTempAndTime(cityName);
			double far = (weather.getTemp() - 273.15) * 9.0/5.0 + 32.0;
			cityInfo.setTemp(far);
		}
		return new ResponseEntity<CityInfo>(cityInfo, HttpStatus.OK);
	}
	
}
