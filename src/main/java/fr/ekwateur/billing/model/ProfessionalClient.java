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
        return revenue > 1_000_000 ? 0.114 : 0.118;
    }

    public double gasPricing() {
        return revenue > 1_000_000 ? 0.111 : 0.113;
    }
}
