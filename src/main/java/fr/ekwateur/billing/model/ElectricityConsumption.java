package fr.ekwateur.billing.model;

public record ElectricityConsumption(double consumptionInKWh) implements Consumption {

    public ElectricityConsumption {
        if (consumptionInKWh < 0) {
            throw new IllegalArgumentException("Consumption cannot be negative");
        }
    }

    public static ElectricityConsumption zero() {
        return new ElectricityConsumption(0);
    }

}
