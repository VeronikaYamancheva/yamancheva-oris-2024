package ru.vhsroni;

import ru.vhsroni.model.CarEntity;
import ru.vhsroni.service.CarService;
import ru.vhsroni.service.PenaltyService;
import ru.vhsroni.service.impl.CarServiceImpl;
import ru.vhsroni.service.impl.PenaltyServiceImpl;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        CarService carService = new CarServiceImpl();
        PenaltyService penaltyService = new PenaltyServiceImpl();

        System.out.println(carService.findAll());
        System.out.println(carService.findCarById(null));
        System.out.println(carService.saveNewCar(new CarEntity(null, "My Best Car")));
        System.out.println(carService.deleteCarById(40L));
        System.out.println(carService.updateCarById(new CarEntity(-100L, "Suzuki Grand Vitara"), 200L));

        System.out.println(penaltyService.findAllByCarId(2L));
        System.out.println(penaltyService.findAllWhereAmountLargerThan(50));
        System.out.println(penaltyService.findAllOlderThanDate(Date.valueOf("2024-10-10")));

    }
}