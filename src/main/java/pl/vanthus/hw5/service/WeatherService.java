package pl.vanthus.hw5.service;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.vanthus.hw5.model.Weather;

@Service
@NoArgsConstructor
public class WeatherService {

    private Weather weather;

    @Value("${APPID}")
    private String APPID;


    public Weather getWeather(String city, String unit) {

        String url = createUrl(city, unit);

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);

        return weather = new Weather(
                jsonNode.get("weather").findValue("main").asText(),
                jsonNode.get("weather").findValue("description").asText(),
                "http://openweathermap.org/img/wn/" + jsonNode.get("weather").findValue("icon").asText() + "@2x.png",
                Float.parseFloat(jsonNode.get("main").get("temp").asText()),
                Float.parseFloat(jsonNode.get("main").get("pressure").asText()),
                Float.parseFloat(jsonNode.get("main").get("humidity").asText()),
                Float.parseFloat(jsonNode.get("wind").get("speed").asText())
        );

    }

    private String createUrl(String city, String unit) {

        StringBuilder url = new StringBuilder();

        return url.append("http://api.openweathermap.org/data/2.5/weather?q=")
                .append(city)
                .append(convertUnitFromForm(unit))
                .append(APPID)
                .toString();
    }

    private String convertUnitFromForm(String unit) {

        if (unit.equals("Fahrenheit")) {
            unit = "&units=imperial";
        } else if (unit.equals("Celsius")) {
            unit = "&units=metric";
        } else {
            unit = "&units=default";
        }

        return unit;
    }
}
