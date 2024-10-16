package fr.ekwateur.billing.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IndividualClientTest {

    @Nested
    @DisplayName("Constructor Validations")
    class ConstructorValidations {

        @Test
        @DisplayName("Should create IndividualClient with valid inputs")
        void testValidIndividualClientCreation() {
            assertDoesNotThrow(() -> {
                var client = new IndividualClient("EKW12345678", "Mr.", "Doe", "John");
                assertEquals("EKW12345678", client.clientReference());
                assertEquals("Mr.", client.civility());
                assertEquals("Doe", client.lastName());
                assertEquals("John", client.firstName());
            });
        }

        @Test
        @DisplayName("Should throw NullPointerException when clientReference is null")
        void testClientReferenceNull() {
            NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                new IndividualClient(null, "Mr.", "Doe", "John");
            });
            assertEquals("'clientReference' cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when clientReference pattern is invalid")
        void testClientReferenceInvalidPattern() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new IndividualClient("INVALID123", "Mr.", "Doe", "John");
            });
            assertEquals("'clientReference' must be EKW + 8 digits", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw NullPointerException when civility is null")
        void testCivilityNull() {
            NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                new IndividualClient("EKW12345678", null, "Doe", "John");
            });
            assertEquals("'civility' cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw NullPointerException when lastName is null")
        void testLastNameNull() {
            NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                new IndividualClient("EKW12345678", "Mr.", null, "John");
            });
            assertEquals("'lastName' cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw NullPointerException when firstName is null")
        void testFirstNameNull() {
            NullPointerException exception = assertThrows(NullPointerException.class, () -> {
                new IndividualClient("EKW12345678", "Mr.", "Doe", null);
            });
            assertEquals("'firstName' cannot be null", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Pricing Methods")
    class PricingMethods {

        @Test
        @DisplayName("Should return correct electricity pricing")
        void testElectricityPricing() {
            var client = new IndividualClient("EKW12345678", "Ms.", "Smith", "Jane");
            assertEquals(0.121, client.electricityPricing());
        }

        @Test
        @DisplayName("Should return correct gas pricing")
        void testGasPricing() {
            var client = new IndividualClient("EKW87654321", "Dr.", "Brown", "Emily");
            assertEquals(0.115, client.gasPricing());
        }
    }
}

