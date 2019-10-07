package az.pashabank.exam.weather_info.service;

import az.pashabank.exam.weather_info.exception.WeatherException;
import az.pashabank.exam.weather_info.model.WeatherDto;

import java.util.List;

public interface WeatherService {
    WeatherDto getWeatherById(Long weatherId) throws WeatherException;

    List<WeatherDto> getWeatherByDate(String date) throws WeatherException;

    WeatherDto getWeatherByDateAndCity(String date, String city) throws WeatherException;

    List<WeatherDto> getWeatherByCity(String city) throws WeatherException;

    void addWeatherInfo(WeatherDto weatherDto) throws WeatherException;

    void updateWeatherInfo(WeatherDto weatherDto) throws WeatherException;

    void deleteWeatherInfo(Long weatherId) throws WeatherException;
}
