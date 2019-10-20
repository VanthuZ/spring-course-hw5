package pl.vanthus.hw5.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.vanthus.hw5.service.WeatherService;

@Route("weather")
@Getter
@Component
public class WeatherGui extends VerticalLayout {

    private TextField cityField;
    private ListBox<String> unitsListBox;
    private Button getWeatherButton;
    private WeatherService weatherService;

    @Autowired
    public WeatherGui(WeatherService weatherService) {
        this.weatherService = weatherService;


        cityField = new TextField("City");

        Label unitsLabel = new Label("Choose unit");
        unitsListBox = new ListBox<>();
        unitsListBox.setItems("Fahrenheit", "Celsius", "Kelvin");

        getWeatherButton = new Button("Check Weather", new Icon(VaadinIcon.SUN_RISE));
        getWeatherButton.addClickListener(event -> weatherService.getWeather(cityField.getValue(), unitsListBox.getValue()));

        add(cityField, unitsLabel, unitsListBox, getWeatherButton);

    }
}
