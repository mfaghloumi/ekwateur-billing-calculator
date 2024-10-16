package fr.ekwateur.billing.model;

public enum Pricing {

    INDIVIDUAL(0.121, 0.115),
    PRO_OVER_1M(0.114, 0.111),
    PRO_BELOW_1M(0.118, 0.113);

    private final double electricityPrice;

    private final double gasPrice;

    Pricing(double electricityPrice, double gasPrice) {
        this.electricityPrice = electricityPrice;
        this.gasPrice = gasPrice;
    }

    public double electricityPrice() {
        return electricityPrice;
    }

    public double gasPrice() {
        return gasPrice;
    }
}
