package com.cst438.assignment2.service;
 
import com.cst438.assignment2.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CityServiceTest {

	@MockBean
	private WeatherService weatherService;
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;


	@Test
	public void contextLoads() {
	}


	@Test
	public void testCityFound() throws Exception {

		Country country = new Country("CTS", "CountryTest");
		City city = new City(1, "CityTest", "CTS", "DistrictTest", 1000);

		List<City> cityList = new ArrayList<City>();
		cityList.add(city);

		given(weatherService.getTempAndTime("CityTest")).willReturn(new TempAndTime(296.99, 1594097934, -14400));
		given(cityRepository.findByName("CityTest")).willReturn(cityList);
		given(countryRepository.findByCode("CTS")).willReturn(country);

		CityInfo resultCityInfo = cityService.getCityInfo("CityTest");
		CityInfo expectedCityInfo = new CityInfo(1, "CityTest", "CTS", "CountryTest", "DistrictTest", 1000, 74.0, "0:58");

		assertThat(resultCityInfo).isEqualTo(expectedCityInfo);
	}

	@Test
	public void  testCityNotFound() {

		Country country = new Country("CTS","CountryTest");
		City city = new City(1, "CityTest","CTS","DistrictTest",1000);
		List<City> cityList = new ArrayList<>();
		cityList.add(city);

		given(weatherService.getTempAndTime("TestCity")).willReturn(new TempAndTime(300, 100000,1000));
		given(cityRepository.findByName("CityTest")).willReturn(cityList);
		given(countryRepository.findByCode("CTS")).willReturn(country);

		CityInfo resultCityInfo = cityService.getCityInfo("Random Name of City");
		CityInfo expectedCityInfo = null;

		assertThat(resultCityInfo).isEqualTo(expectedCityInfo);
	}

	@Test 
	public void  testCityMultiple() {
		Country country = new Country("CTS", "CountryTest");

		List<City> cityList = new ArrayList<City>();
		City city1 = new City(1, "CityTest", "CTS", "DistrictTest", 9000);
		cityList.add(city1);
		City city2 = new City(1, "CityTest", "CTS", "DistrictTest2", 9000);
		cityList.add(city2);
		City city3 = new City(1, "CityTest", "CTS", "DistrictTest3", 9000);
		cityList.add(city3);
		City city4 = new City(1, "CityTest", "CTS", "DistrictTest4", 9000);
		cityList.add(city4);

		given(weatherService.getTempAndTime("CityTest")).willReturn(new TempAndTime(296.99, 1594097934, -14400));
		given(cityRepository.findByName("CityTest")).willReturn(cityList);
		given(countryRepository.findByCode("CTS")).willReturn(country);

		CityInfo resultCityInfo = cityService.getCityInfo("CityTest");
		CityInfo expectedCityInfo = new CityInfo(1, "CityTest", "CTS", "CountryTest", "DistrictTest", 9000, 74.0, "0:58");
		assertThat(resultCityInfo).isEqualTo(expectedCityInfo);
	}
}
