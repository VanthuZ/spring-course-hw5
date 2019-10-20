package pl.vanthus.hw5.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.vanthus.hw5.model.Weather;

@Service
public class WeatherService {

    Weather weather;

    @EventListener(ApplicationReadyEvent.class)
    public void connect(){

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode =  restTemplate.getForObject("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22", JsonNode.class);

        weather = new Weather(
                jsonNode.get("weather").findValue("main").asText(),
                jsonNode.get("weather").findValue("description").asText(),
                jsonNode.get("weather").findValue("icon").asText(),
                Float.parseFloat(jsonNode.get("main").get("temp").asText()),
                Float.parseFloat(jsonNode.get("main").get("pressure").asText()),
                Float.parseFloat(jsonNode.get("main").get("humidity").asText()),
                Float.parseFloat(jsonNode.get("wind").get("speed").asText())
                );

        System.out.println(weather);
    }
}
