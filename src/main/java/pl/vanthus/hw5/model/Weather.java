package pl.vanthus.hw5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Weather {

    private String mainDescription;
    private String description;
    private String icon;
    private float temp;
    private float pressure;
    private float humidity;
    private float windSpeed;

}
