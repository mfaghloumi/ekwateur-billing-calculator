package fr.ekwateur.billing;

import fr.ekwateur.billing.model.ElectricityConsumption;
import fr.ekwateur.billing.model.GasConsumption;
import fr.ekwateur.billing.model.IndividualClient;
import fr.ekwateur.billing.model.MonthlyConsumption;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        var individualClient = new IndividualClient("EKW00000001", "Mr.", "Doe", "John");
        var monthlyConsumptions = List.of(
                new MonthlyConsumption(individualClient, new GasConsumption(200.0), ElectricityConsumption.zero())
        );

        for (var mc : monthlyConsumptions) {
            var revenue = mc.calculateBillingAmount();
            System.out.println(generateInvoice(mc, revenue));
        }
    }

    public static String generateInvoice(MonthlyConsumption consumption, double amountDue) {
        return """
                Invoice for %s
                Electricity Consumption: %.2f kWh
                Gas Consumption: %.2f kWh
                Amount Due: %.2f €
                """.formatted(
                consumption.client().clientReference(),
                consumption.electricity().consumptionInKWh(),
                consumption.gas().consumptionInKWh(),
                amountDue
        );
    }
}
