package fr.ekwateur.billing.model;

import static java.util.Objects.requireNonNull;

public record IndividualClient(String clientReference,
                               String civility,
                               String lastName,
                               String firstName) implements Client {

    public IndividualClient {
        requireNonNull(clientReference, "'clientReference' cannot be null");
        requireNonNull(civility, "'civility' cannot be null");
        requireNonNull(lastName, "'lastName' cannot be null");
        requireNonNull(firstName, "'firstName' cannot be null");
        if (!CLIENT_REFERENCE_PATTERN.matcher(clientReference).matches()) {
            throw new IllegalArgumentException("'clientReference' must be EKW + 8 digits");
        }
    }

    @Override
    public double electricityPricing() {
        return 0.121;
    }

    @Override
    public double gasPricing() {
        return 0.115;
    }
}
