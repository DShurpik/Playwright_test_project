package pages.sauceDemo.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipInfo {
    // Работает как билдер для передачи данных в метод класса
    // Лучше создавать модели и передавать на вход метода, чем писать 100500 данных
    private String firstName;
    private String lastName;
    private String zip;
}
