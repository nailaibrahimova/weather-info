package az.pashabank.exam.weather_info.mapper;

import az.pashabank.exam.weather_info.dao.entity.WeatherEntity;
import az.pashabank.exam.weather_info.model.WeatherDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WeatherMapper {
    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    WeatherEntity mapDtoToEntity(WeatherDto weatherDto);

    WeatherDto mapEntityToDto(WeatherEntity weatherEntity);
}
