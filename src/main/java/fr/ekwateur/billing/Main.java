package fr.ekwateur.billing;

import fr.ekwateur.billing.model.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        var individualClient = new IndividualClient("EKW00000001", "Mr.", "Doe", "John");
        var proClient1M = new ProfessionalClient("EKW00000002", "12345678901234", "Acme Corp", 1_500_000);
        var proClient9K = new ProfessionalClient("EKW00000003", "12345678901235", "Acme Corp", 900_000);

        var monthlyConsumptions = List.of(
                new MonthlyConsumption(individualClient, new GasConsumption(200.0), ElectricityConsumption.zero()),
                new MonthlyConsumption(proClient9K, GasConsumption.zero(), new ElectricityConsumption(200)),
                new MonthlyConsumption(proClient1M, new GasConsumption(200.0), new ElectricityConsumption(200))
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
