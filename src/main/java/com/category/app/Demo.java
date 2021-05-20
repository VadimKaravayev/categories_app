package com.category.app;

import java.io.IOException;

import com.category.app.strategies.print.impl.CarsPrintStrategy;

import static com.category.app.constants.Constants.ANIMALS;
import static com.category.app.constants.Constants.CARS;
import static com.category.app.constants.Constants.CONFIG_FILE;
import static com.category.app.constants.Constants.INPUT_FILE_PATH;
import static com.category.app.constants.Constants.INPUT_TWO_FILE_PATH;
import static com.category.app.constants.Constants.NUMBERS;

public class Demo {
    public static void main(String[] args) throws IOException {
        CategoryApp app = new CategoryApp(CONFIG_FILE);

        app.loadData(INPUT_FILE_PATH);
        System.out.println(app.getProcessedData());

        app.printCategory(ANIMALS);
        app.printCategory(NUMBERS);

        app.addCategory(CARS);
        app.addPrintStrategy(CARS, new CarsPrintStrategy());

        app.loadData(INPUT_TWO_FILE_PATH);
        System.out.println(app.getProcessedData());

        app.printCategory(CARS);
    }
}
