package fr.ekwateur.billing.model;

public record GasConsumption(double consumptionInKWh) implements Consumption {

    public GasConsumption {
        if (consumptionInKWh < 0) {
            throw new IllegalArgumentException("Consumption cannot be negative");
        }
    }

    public static GasConsumption zero() {
        return new GasConsumption(0);
    }

}
