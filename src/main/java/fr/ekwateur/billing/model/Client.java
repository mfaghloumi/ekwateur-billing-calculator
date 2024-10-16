package fr.ekwateur.billing.model;

import java.util.regex.Pattern;

public sealed interface Client permits IndividualClient {

    Pattern CLIENT_REFERENCE_PATTERN = Pattern.compile("EKW\\d{8}");

    String clientReference();

    double electricityPricing();

    double gasPricing();

}
