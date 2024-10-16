package fr.ekwateur.billing.model;

public sealed interface Consumption permits ElectricityConsumption, GasConsumption {

    double consumptionInKWh();

}
