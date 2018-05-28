package com.vaadin.tutorial.calculator;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends HorizontalLayout {

    public MainView() {
        TextField input = new TextField("Input starting number");
        input.setId("numberInput");
        input.setRequired(true);
        input.setRequiredIndicatorVisible(true);
        input.setValue("0");

        Button button = new Button("Increase!", event -> {
            Integer value = Integer.parseInt(input.getValue());
            Integer result = CalculatorService.add(value);
            input.setValue(Integer.toString(result));
        });
        button.setId("addButton");

        add(input, button);
        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, button);
    }
}