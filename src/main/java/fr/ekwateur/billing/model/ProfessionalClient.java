package fr.ekwateur.billing.model;

import static java.util.Objects.requireNonNull;

public record ProfessionalClient(String clientReference,
                                 String siretNumber,
                                 String companyName,
                                 double revenue) implements Client {

    public ProfessionalClient {
        requireNonNull(clientReference, "'clientReference' cannot be null");
        requireNonNull(siretNumber, "'siretNumber' cannot be null");
        requireNonNull(companyName, "'companyName' cannot be null");
        if (!CLIENT_REFERENCE_PATTERN.matcher(clientReference).matches()) {
            throw new IllegalArgumentException("'clientReference' must be EKW + 8 digits");
        }
        if (revenue <= 0) {
            throw new IllegalArgumentException("'revenue' cannot be equal or below 0");
        }
        if (revenue == 1_000_000) {
            throw new UnsupportedOperationException("'revenue' cannot be equal to 1M for the moment");
        }
    }

    public double electricityPricing() {
        return pricing().electricityPrice();
    }

    public double gasPricing() {
        return pricing().gasPrice();
    }

    private Pricing pricing() {
        return revenue > 1_000_000 ? Pricing.PRO_OVER_1M : Pricing.PRO_BELOW_1M;
    }
}
