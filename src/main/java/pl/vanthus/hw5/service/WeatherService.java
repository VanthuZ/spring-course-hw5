package pl.vanthus.hw5.service;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.NoArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.vanthus.hw5.model.Weather;

@Service
@NoArgsConstructor
public class WeatherService {

    Weather weather;




    public Weather getWeather(String city, String unit){

        String url = createUrl(city, unit);

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode =  restTemplate.getForObject(url, JsonNode.class);

        weather = new Weather(
                jsonNode.get("weather").findValue("main").asText(),
                jsonNode.get("weather").findValue("description").asText(),
                "http://openweathermap.org/img/wn/" + jsonNode.get("weather").findValue("icon").asText() + "@2x.png",
                Float.parseFloat(jsonNode.get("main").get("temp").asText()),
                Float.parseFloat(jsonNode.get("main").get("pressure").asText()),
                Float.parseFloat(jsonNode.get("main").get("humidity").asText()),
                Float.parseFloat(jsonNode.get("wind").get("speed").asText())
                );

       return weather;

    }

    private String createUrl(String city, String unit){

        StringBuilder url = new StringBuilder();

        url.append("http://api.openweathermap.org/data/2.5/weather?")
                .append("q=")
                .append(city)
                .append(convertUnitFromForm(unit))
                .append("&APPID=7f861b6c901f42b7e79cd9b3cf7b646f");

        return url.toString();
    }

    private String convertUnitFromForm(String unit){

       if(unit.equals("Fahrenheit")){
           unit = "&units=imperial";
       }else if(unit.equals("Celsius")){
           unit = "&units=metric";
       }else{
           unit = "&units=default";
       }

       return unit;
    }
}
