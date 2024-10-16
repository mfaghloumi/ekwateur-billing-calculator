package fr.ekwateur.billing.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MonthlyConsumptionTest {

    @Nested
    @DisplayName("Calculate Billing Amount Tests")
    class CalculateBillingAmountTests {

        @Test
        @DisplayName("Should calculate billing amount for IndividualClient with both gas and electricity consumption")
        void testIndividualClientWithGasAndElectricity() {
            // Arrange
            IndividualClient client = new IndividualClient("EKW12345678", "Mr.", "Doe", "John");
            GasConsumption gasConsumption = new GasConsumption(100.0);
            ElectricityConsumption electricityConsumption = new ElectricityConsumption(200.0);

            MonthlyConsumption monthlyConsumption = new MonthlyConsumption(client, gasConsumption, electricityConsumption);

            // Act
            double billingAmount = monthlyConsumption.calculateBillingAmount();

            // Assert
            assertEquals(35.70, billingAmount, 0.001);
        }

        @Test
        @DisplayName("Should calculate billing amount for ProfessionalClient (revenue < 1,000,000) with gas only")
        void testProfessionalClientBelowThresholdWithGasOnly() {
            // Arrange
            ProfessionalClient client = new ProfessionalClient("EKW87654321", "12345678901234", "Startup Inc", 500_000);
            GasConsumption gasConsumption = new GasConsumption(300.0);
            ElectricityConsumption electricityConsumption = ElectricityConsumption.zero();

            MonthlyConsumption monthlyConsumption = new MonthlyConsumption(client, gasConsumption, electricityConsumption);

            // Act
            double billingAmount = monthlyConsumption.calculateBillingAmount();

            // Assert
            assertEquals(33.90, billingAmount, 0.001);
        }

        @Test
        @DisplayName("Should calculate billing amount for ProfessionalClient (revenue > 1,000,000) with electricity only")
        void testProfessionalClientAboveThresholdWithElectricityOnly() {
            // Arrange
            ProfessionalClient client = new ProfessionalClient("EKW23456789", "98765432109876", "Global Corp", 2_000_000);
            GasConsumption gasConsumption = GasConsumption.zero();
            ElectricityConsumption electricityConsumption = new ElectricityConsumption(400.0);

            MonthlyConsumption monthlyConsumption = new MonthlyConsumption(client, gasConsumption, electricityConsumption);

            // Act
            double billingAmount = monthlyConsumption.calculateBillingAmount();

            // Assert
            assertEquals(45.60, billingAmount, 0.001);
        }

        @Test
        @DisplayName("Should calculate billing amount with zero consumption")
        void testZeroConsumption() {
            // Arrange
            IndividualClient client = new IndividualClient("EKW34567890", "Ms.", "Smith", "Jane");
            GasConsumption gasConsumption = GasConsumption.zero();
            ElectricityConsumption electricityConsumption = ElectricityConsumption.zero();

            MonthlyConsumption monthlyConsumption = new MonthlyConsumption(client, gasConsumption, electricityConsumption);

            // Act
            double billingAmount = monthlyConsumption.calculateBillingAmount();

            // Assert
            assertEquals(0.0, billingAmount, 0.001);
        }

        @Test
        @DisplayName("Should throw NullPointerException when client is null")
        void testNullClient() {
            // Arrange
            GasConsumption gasConsumption = new GasConsumption(100.0);
            ElectricityConsumption electricityConsumption = new ElectricityConsumption(200.0);

            // Act & Assert
            NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                new MonthlyConsumption(null, gasConsumption, electricityConsumption);
            });
            assertEquals("Client cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw NullPointerException when gas consumption is null")
        void testNullGasConsumption() {
            // Arrange
            IndividualClient client = new IndividualClient("EKW45678901", "Dr.", "Brown", "Emily");
            ElectricityConsumption electricityConsumption = new ElectricityConsumption(150.0);

            // Act & Assert
            NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                new MonthlyConsumption(client, null, electricityConsumption);
            });
            assertEquals("Gas consumption cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw NullPointerException when electricity consumption is null")
        void testNullElectricityConsumption() {
            // Arrange
            IndividualClient client = new IndividualClient("EKW56789012", "Mr.", "Johnson", "Mike");
            GasConsumption gasConsumption = new GasConsumption(200.0);

            // Act & Assert
            NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                new MonthlyConsumption(client, gasConsumption, null);
            });
            assertEquals("Electricity consumption cannot be null", exception.getMessage());
        }
    }
}

