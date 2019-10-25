package pl.vanthus.hw5.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

import pl.vanthus.hw5.model.Weather;
import pl.vanthus.hw5.service.WeatherService;

@Route("weather")
public class WeatherGui extends VerticalLayout {

    private TextField cityField;
    private ListBox<String> unitsListBox;
    private Button getWeatherButton;
    private WeatherService weatherService;
    private Label unitsLabel;
    private Label weatherDescriptionLabel;
    private Label tempLabel;
    private Label pressureLabel;
    private Label humidityLabel;
    private Label windSpeedLabel;
    private Image weatherImage;

    @Autowired
    public WeatherGui(WeatherService weatherService) {
        this.weatherService = weatherService;

        initFields();
        getWeatherButton.addClickListener(event -> showWeather(weatherService.getWeather(cityField.getValue(), unitsListBox.getValue())));

    }

    private void showWeather(Weather weather) {

        weatherDescriptionLabel.setText("Description: " + weather.getDescription());
        tempLabel.setText("Temperature: " + weather.getTemp());
        pressureLabel.setText("Pressure: " + weather.getPressure());
        humidityLabel.setText("Humidity: " + weather.getHumidity());
        windSpeedLabel.setText("Wind speed: " + weather.getWindSpeed());
        weatherImage.setSrc(weather.getIcon());
    }

    private void initFields() {

        cityField = new TextField("City");
        unitsLabel = new Label("Choose unit");
        unitsListBox = new ListBox<>();
        unitsListBox.setItems("Fahrenheit", "Celsius", "Kelvin");
        weatherDescriptionLabel = new Label();
        tempLabel = new Label();
        pressureLabel = new Label();
        humidityLabel = new Label();
        windSpeedLabel = new Label();
        weatherImage = new Image();
        getWeatherButton = new Button("Check Weather", new Icon(VaadinIcon.SUN_RISE));

        add(cityField, unitsLabel, unitsListBox, getWeatherButton);
        add(weatherDescriptionLabel, weatherImage, tempLabel, pressureLabel, humidityLabel, windSpeedLabel);
    }
}
