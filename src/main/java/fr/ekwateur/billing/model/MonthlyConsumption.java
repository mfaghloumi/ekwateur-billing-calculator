package fr.ekwateur.billing.model;

import static java.util.Objects.requireNonNull;

public record MonthlyConsumption(Client client,
                                 GasConsumption gas,
                                 ElectricityConsumption electricity
) {

    public MonthlyConsumption {
        requireNonNull(client, "Client cannot be null");
        requireNonNull(gas, "Gas consumption cannot be null");
        requireNonNull(electricity, "Electricity consumption cannot be null");
    }

    public double calculateBillingAmount() {
        return client.electricityPricing() * electricity.consumptionInKWh() +
                client.gasPricing() * gas.consumptionInKWh();
    }

}
