package fr.ekwateur.billing.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ElectricityConsumptionTest {

    @Test
    @DisplayName("Should create ElectricityConsumption with specified consumption")
    void testElectricityConsumptionCreation() {
        ElectricityConsumption electricityConsumption = new ElectricityConsumption(200.0);
        assertEquals(200.0, electricityConsumption.consumptionInKWh());
    }

    @Test
    @DisplayName("Static method zero() should return ElectricityConsumption with zero consumption")
    void testElectricityConsumptionZero() {
        ElectricityConsumption electricityConsumption = ElectricityConsumption.zero();
        assertEquals(0.0, electricityConsumption.consumptionInKWh());
    }

    @Test
    @DisplayName("Should allow zero consumption")
    void testElectricityConsumptionZeroValue() {
        ElectricityConsumption electricityConsumption = new ElectricityConsumption(0.0);
        assertEquals(0.0, electricityConsumption.consumptionInKWh());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when consumption is negative")
    void testElectricityConsumptionNegativeValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ElectricityConsumption(-100.0);
        });
        assertEquals("Consumption cannot be negative", exception.getMessage());
    }
}
