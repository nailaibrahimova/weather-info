package az.pashabank.exam.weather_info.dao;

import az.pashabank.exam.weather_info.dao.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    List<WeatherEntity> findAllByDate(LocalDate date);

    Optional<WeatherEntity> findAllByDateAndCity(LocalDate date, String city);

    List<WeatherEntity> findAllByCity(String city);
}
