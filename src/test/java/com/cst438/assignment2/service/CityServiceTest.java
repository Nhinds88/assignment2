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
		Country country = new Country("CTS", "Country_Test");

		given(countryRepository.findByCode("CTS")).willReturn(country);

		City city = new City(1, "Test_City", "CTS", "District_Test", 9000);

		List<City> cityList = new ArrayList<City>();
		cityList.add(city);

		given(weatherService.getTempAndTime("Test_City")).willReturn(new TempAndTime(301.66, 1593997621, -14400));
		given(cityRepository.findByName("Test_City")).willReturn(cityList);

		CityInfo cityInfo = cityService.getCityInfo("Test_City");
		CityInfo expectedCityInfo = new CityInfo(1, "Test_City", "CTS", "Country_Test", "District_test", 9000, 83.31800000000008, "1593997621");

		assertThat(cityInfo).isEqualTo(expectedCityInfo);
	}
	
	@Test 
	public void  testCityNotFound() {
		Country country = new Country("CTS", "Country_Test");

		given(countryRepository.findByCode("CTS")).willReturn(country);

		City city = new City(1, "Test_City", "CTS", "District_Test", 9000);

		List<City> cityList = new ArrayList<City>();
		cityList.add(city);

		given(weatherService.getTempAndTime("Test_City")).willReturn(new TempAndTime(301.66, 1593997621, -14400));
		given(cityRepository.findByName("Test_City")).willReturn(cityList);

		CityInfo cityInfo = cityService.getCityInfo("No_City");
		CityInfo expectedCityInfo = null;

		assertThat(cityInfo).isEqualTo(expectedCityInfo);
	}
	
	@Test 
	public void  testCityMultiple() {
		Country country = new Country("CTS", "Country_Test");

		given(countryRepository.findByCode("CTS")).willReturn(country);

		List<City> cityList = new ArrayList<City>();
		City city1 = new City(1, "Test_City", "CTS", "District_Test1", 9000);
		cityList.add(city1);
		City city2 = new City(2, "Test_City", "CTS", "District_Test2", 10000);
		cityList.add(city2);
		City city3 = new City(3, "Test_City", "CTS", "District_Test3", 11000);
		cityList.add(city3);
		City city4 = new City(4, "Test_City", "CTS", "District_Test4", 12000);
		cityList.add(city4);

		given(weatherService.getTempAndTime("Test_City")).willReturn(new TempAndTime(301.66, 1593997621, -14400));

		given(cityRepository.findByName("Test_City")).willReturn(cityList);

		CityInfo cityInfo = cityService.getCityInfo("Test_City");
		CityInfo expectedCityInfo = new CityInfo(1, "Test_City", "CTS", "Country_Test", "District_test", 9000, 83.31800000000008, "1593997621");

		assertThat(cityInfo).isEqualTo(expectedCityInfo);
	}

}
