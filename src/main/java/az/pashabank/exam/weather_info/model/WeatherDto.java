package az.pashabank.exam.weather_info.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {

    private Long id;

    private double temperature;

    private LocalDate date;

    @NotNull
    private String city;
}
