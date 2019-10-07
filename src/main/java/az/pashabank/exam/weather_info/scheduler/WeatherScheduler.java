package az.pashabank.exam.weather_info.scheduler;

import az.pashabank.exam.weather_info.dao.WeatherRepository;
import az.pashabank.exam.weather_info.dao.entity.WeatherEntity;
import az.pashabank.exam.weather_info.util.ListOfCities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.Random;

@Configuration
@EnableScheduling
public class WeatherScheduler {

    private WeatherRepository weatherRepository;

    private Logger logger = LoggerFactory.getLogger(WeatherScheduler.class);

    public WeatherScheduler(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void addRandomWeatherInfo(){
        logger.info("ActionLog.addRandomWeatherInfo");
        WeatherEntity weatherEntity = new WeatherEntity();
        Random random = new Random();
        String city = ListOfCities.cities[random.nextInt(ListOfCities.cities.length)];
        double temperature = random.nextDouble();
        LocalDate localDate = LocalDate.now();

        weatherEntity.setCity(city);
        weatherEntity.setDate(localDate);
        weatherEntity.setTemperature(temperature);

        weatherRepository.save(weatherEntity);
        logger.info("ActionLog.addRandomWeatherInfo: SUCCESS");
    }
}
