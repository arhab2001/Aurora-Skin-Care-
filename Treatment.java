public class Treatment {
    private String treatmentType;
    private double price;

    public Treatment(String treatmentType, double price) {
        this.treatmentType = treatmentType;
        this.price = price;
    }

    // Getters
    public String getTreatmentType() {
        return treatmentType;
    }

    public double getPrice() {
        return price;
    }

    // Available treatments
    public static Treatment[] getAvailableTreatments() {
        return new Treatment[] {
            new Treatment("Acne Treatment", 2750.00),
            new Treatment("Skin Whitening", 7650.00),
            new Treatment("Mole Removal", 3850.00),
            new Treatment("Laser Treatment", 12500.00)
        };
    }
}
