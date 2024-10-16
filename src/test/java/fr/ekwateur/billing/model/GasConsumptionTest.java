package fr.ekwateur.billing.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GasConsumptionTest {

    @Test
    @DisplayName("Should create GasConsumption with specified consumption")
    void testGasConsumptionCreation() {
        GasConsumption gasConsumption = new GasConsumption(150.0);
        assertEquals(150.0, gasConsumption.consumptionInKWh());
    }

    @Test
    @DisplayName("Static method zero() should return GasConsumption with zero consumption")
    void testGasConsumptionZero() {
        GasConsumption gasConsumption = GasConsumption.zero();
        assertEquals(0.0, gasConsumption.consumptionInKWh());
    }

    @Test
    @DisplayName("Should allow zero consumption")
    void testGasConsumptionZeroValue() {
        GasConsumption gasConsumption = new GasConsumption(0.0);
        assertEquals(0.0, gasConsumption.consumptionInKWh());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when consumption is negative")
    void testGasConsumptionNegativeValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new GasConsumption(-50.0);
        });
        assertEquals("Consumption cannot be negative", exception.getMessage());
    }
}
