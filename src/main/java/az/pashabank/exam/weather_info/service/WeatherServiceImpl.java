package az.pashabank.exam.weather_info.service;

import az.pashabank.exam.weather_info.dao.WeatherRepository;
import az.pashabank.exam.weather_info.dao.entity.WeatherEntity;
import az.pashabank.exam.weather_info.exception.WeatherException;
import az.pashabank.exam.weather_info.mapper.WeatherMapper;
import az.pashabank.exam.weather_info.model.WeatherDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {
    private WeatherRepository weatherRepository;

    private Logger logger = LoggerFactory.getLogger(WeatherService.class);

    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public WeatherDto getWeatherById(Long weatherId) throws WeatherException {
        logger.info("ActionLog.getWeatherById");
        WeatherEntity weatherEntity = weatherRepository.findById(weatherId)
                .orElseThrow(() -> new WeatherException("ActionLog.getWeatherById: no weather info with id=" + weatherId));
        return WeatherMapper.INSTANCE.mapEntityToDto(weatherEntity);
    }

    @Override
    public List<WeatherDto> getWeatherByDate(String date) throws WeatherException {
        logger.info("ActionLog.getWeatherByDate");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localDate = LocalDate.parse(date, formatter);
        List<WeatherEntity> weatherEntities = weatherRepository.findAllByDate(localDate);
        if (CollectionUtils.isEmpty(weatherEntities)) {
            WeatherException weatherException = new WeatherException("No weather info for such date");
            logger.error("", weatherException);
            throw weatherException;
        } else {
            return weatherEntities.stream().map(WeatherMapper.INSTANCE::mapEntityToDto).collect(Collectors.toList());
        }
    }

    @Override
    public WeatherDto getWeatherByDateAndCity(String date, String city) throws WeatherException {
        logger.info("ActionLog.getWeatherByDateAndCity");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localDate = LocalDate.parse(date, formatter);
        WeatherEntity weatherEntity = weatherRepository.findAllByDateAndCity(localDate, city)
                .orElseThrow(() -> new WeatherException("No weather info for date=" + date + " and city='" + city + "'"));
        return WeatherMapper.INSTANCE.mapEntityToDto(weatherEntity);
    }

    @Override
    public List<WeatherDto> getWeatherByCity(String city) throws WeatherException {
        List<WeatherEntity> weatherEntities = weatherRepository.findAllByCity(city);
        if (!CollectionUtils.isEmpty(weatherEntities)) {
            return weatherEntities.stream().map(WeatherMapper.INSTANCE::mapEntityToDto).collect(Collectors.toList());
        } else {
            WeatherException weatherException = new WeatherException("No weather info for such date");
            logger.error("", weatherException);
            throw weatherException;
        }
    }

    @Override
    public void addWeatherInfo(WeatherDto weatherDto) throws WeatherException {
        logger.info("ActionLog.addWeatherInfo");
        WeatherEntity weatherEntity = WeatherMapper.INSTANCE.mapDtoToEntity(weatherDto);
        weatherRepository.save(weatherEntity);
        logger.info("ActionLog.addWeatherInfo: SUCCESS");
    }

    @Override
    public void updateWeatherInfo(WeatherDto weatherDto) throws WeatherException {
        logger.info("ActionLog.updateWeatherInfo");
        Long weatherId = weatherDto.getId();
        if (weatherRepository.existsById(weatherId)) {
            WeatherEntity weatherEntity = WeatherMapper.INSTANCE.mapDtoToEntity(weatherDto);
            weatherRepository.save(weatherEntity);
            logger.info("ActionLog.updateWeatherInfo: SUCCESS");
        } else {
            WeatherException weatherException = new WeatherException("No weather info id=" + weatherId);
            logger.error("", weatherException);
            throw weatherException;
        }
    }

    @Override
    public void deleteWeatherInfo(Long weatherId) throws WeatherException {
        logger.info("ActionLog.deleteWeatherInfo");
        if (weatherRepository.existsById(weatherId)) {
            weatherRepository.deleteById(weatherId);
            logger.info("ActionLog.deleteWeatherInfo: SUCCESS");
        } else {
            WeatherException weatherException = new WeatherException("No weather info with id=" + weatherId);
            logger.error("", weatherException);
            throw weatherException;
        }
    }
}
