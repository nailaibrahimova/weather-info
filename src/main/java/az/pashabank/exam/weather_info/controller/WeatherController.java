package az.pashabank.exam.weather_info.controller;

import az.pashabank.exam.weather_info.model.WeatherDto;
import az.pashabank.exam.weather_info.service.WeatherService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/weather")
@Validated
public class WeatherController {

    private WeatherService weatherService;

    private Logger logger = LoggerFactory.getLogger(WeatherController.class);

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("getById/{weatherId}")
    public WeatherDto getWeatherById(@Min(value = 1, message = "ID cannot be less than 1")
                                     @PathVariable Long weatherId) {
        logger.info("ActionLog.getWeatherById");
        return weatherService.getWeatherById(weatherId);
    }

    @GetMapping("getByDate/{date}")
    public List<WeatherDto> getWeatherByDate(@PathVariable String date) {
        logger.info("ActionLog.getWeatherByDate");
        return weatherService.getWeatherByDate(date);
    }

    @GetMapping("getByDate/{date}/city/{city}")
    public WeatherDto getWeatherByDateAndCity(@PathVariable String date, @PathVariable String city) {
        logger.info("ActionLog.getWeatherByDateAndCity");
        return weatherService.getWeatherByDateAndCity(date, city);
    }

    @GetMapping("getByCity/{city}")
    public List<WeatherDto> getWeatherByCity(@PathVariable String city) {
        logger.info("ActionLog.getWeatherByDateAndCity");
        return weatherService.getWeatherByCity(city);
    }

    @PostMapping("add/{weatherId}")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public void addWeatherInfo(@PathVariable Long weatherId, @Valid @RequestBody WeatherDto weatherDto) {
        logger.info("ActionLog.addWeatherInfo");
        weatherDto.setId(weatherId);
        weatherService.addWeatherInfo(weatherDto);
        logger.info("ActionLog.addWeatherInfo: SUCCESS");
    }

    @PutMapping("update/{weatherId}")
    public void updateWeatherInfo(@Min(value = 1, message = "ID cannot be less than 1")
                                      @PathVariable Long weatherId, @Valid @RequestBody WeatherDto weatherDto) {
        logger.info("ActionLog.updateWeatherInfo");
        weatherDto.setId(weatherId);
        weatherService.updateWeatherInfo(weatherDto);
        logger.info("ActionLog.updateWeatherInfo: SUCCESS");
    }

    @DeleteMapping("delete/{weatherId}")
    public void deleteWeatherInfo(@Min(value = 1, message = "ID cannot be less than 1")
                                      @PathVariable Long weatherId) {
        logger.info("ActionLog.deleteWeatherInfo");
        weatherService.deleteWeatherInfo(weatherId);
        logger.info("ActionLog.deleteWeatherInfo: SUCCESS");
    }


}
