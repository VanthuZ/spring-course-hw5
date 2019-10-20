package pl.vanthus.hw5.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("weather")
public class WeatherGui extends VerticalLayout {

    TextField cityField;
    ListBox<String> unitsListBox;
    Button getWeatherButton;

    public WeatherGui() {

        cityField = new TextField("City");

        Label unitsLabel = new Label("Choose unit");
        unitsListBox = new ListBox<>();
        unitsListBox.setItems("Fahrenheit", "Celsius", "Kelvin");

        getWeatherButton = new Button("Check Weather", new Icon(VaadinIcon.SUN_RISE));


        add(cityField, unitsLabel, unitsListBox, getWeatherButton);

    }
}
