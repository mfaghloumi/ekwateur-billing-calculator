package fr.ekwateur.billing.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfessionalClientTest {

    @Nested
    @DisplayName("Constructor Validations")
    class ConstructorValidations {

        @Test
        @DisplayName("Should create ProfessionalClient with valid inputs (revenue below 1,000,000)")
        void testValidProfessionalClientCreationBelowThreshold() {
            assertDoesNotThrow(() -> {
                var client = new ProfessionalClient("EKW12345678", "12345678901234", "Acme Corp", 900_000);
                assertEquals("EKW12345678", client.clientReference());
                assertEquals("12345678901234", client.siretNumber());
                assertEquals("Acme Corp", client.companyName());
                assertEquals(900_000, client.revenue());
            });
        }

        @Test
        @DisplayName("Should create ProfessionalClient with valid inputs (revenue above 1,000,000)")
        void testValidProfessionalClientCreationAboveThreshold() {
            assertDoesNotThrow(() -> {
                var client = new ProfessionalClient("EKW87654321", "98765432109876", "Global Industries", 1_500_000);
                assertEquals("EKW87654321", client.clientReference());
                assertEquals("98765432109876", client.siretNumber());
                assertEquals("Global Industries", client.companyName());
                assertEquals(1_500_000, client.revenue());
            });
        }

        @Test
        @DisplayName("Should throw NullPointerException when clientReference is null")
        void testClientReferenceNull() {
            NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                new ProfessionalClient(null, "12345678901234", "Acme Corp", 900_000);
            });
            assertEquals("'clientReference' cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw NullPointerException when siretNumber is null")
        void testSiretNumberNull() {
            NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                new ProfessionalClient("EKW12345678", null, "Acme Corp", 900_000);
            });
            assertEquals("'siretNumber' cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw NullPointerException when companyName is null")
        void testCompanyNameNull() {
            NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                new ProfessionalClient("EKW12345678", "12345678901234", null, 900_000);
            });
            assertEquals("'companyName' cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when clientReference pattern is invalid")
        void testClientReferenceInvalidPattern() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new ProfessionalClient("INVALID123", "12345678901234", "Acme Corp", 900_000);
            });
            assertEquals("'clientReference' must be EKW + 8 digits", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when revenue is zero")
        void testRevenueZero() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new ProfessionalClient("EKW12345678", "12345678901234", "Acme Corp", 0);
            });
            assertEquals("'revenue' cannot be equal or below 0", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when revenue is negative")
        void testRevenueNegative() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new ProfessionalClient("EKW12345678", "12345678901234", "Acme Corp", -500_000);
            });
            assertEquals("'revenue' cannot be equal or below 0", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw UnsupportedOperationException when revenue equals 1,000,000")
        void testRevenueEqualsOneMillion() {
            UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
                new ProfessionalClient("EKW12345678", "12345678901234", "Acme Corp", 1_000_000);
            });
            assertEquals("'revenue' cannot be equal to 1M for the moment", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Pricing Methods")
    class PricingMethods {

        @Test
        @DisplayName("Should return correct electricity pricing for revenue below 1,000,000")
        void testElectricityPricingBelowThreshold() {
            var client = new ProfessionalClient("EKW12345678", "12345678901234", "Acme Corp", 900_000);
            assertEquals(0.118, client.electricityPricing());
        }

        @Test
        @DisplayName("Should return correct gas pricing for revenue below 1,000,000")
        void testGasPricingBelowThreshold() {
            var client = new ProfessionalClient("EKW87654321", "98765432109876", "Global Industries", 900_000);
            assertEquals(0.113, client.gasPricing());
        }

        @Test
        @DisplayName("Should return correct electricity pricing for revenue above 1,000,000")
        void testElectricityPricingAboveThreshold() {
            var client = new ProfessionalClient("EKW23456789", "23456789012345", "Big Corp", 1_500_000);
            assertEquals(0.114, client.electricityPricing());
        }

        @Test
        @DisplayName("Should return correct gas pricing for revenue above 1,000,000")
        void testGasPricingAboveThreshold() {
            var client = new ProfessionalClient("EKW34567890", "34567890123456", "Mega Corp", 2_000_000);
            assertEquals(0.111, client.gasPricing());
        }

        @Test
        @DisplayName("Should throw UnsupportedOperationException when revenue equals 1,000,000 for pricing methods")
        void testPricingWithRevenueEqualsOneMillion() {
            UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
                var client = new ProfessionalClient("EKW12345678", "12345678901234", "Acme Corp", 1_000_000);
                client.electricityPricing();
            });
            assertEquals("'revenue' cannot be equal to 1M for the moment", exception.getMessage());
        }
    }
}
