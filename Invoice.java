public class Invoice {
    private Appointment appointment;
    private Treatment treatment;
    private double totalAmount;
    private final double taxRate = 0.025;

    public Invoice(Appointment appointment, Treatment treatment) {
        this.appointment = appointment;
        this.treatment = treatment;
        calculateTotal();
    }

    // Method to calculate total
    private void calculateTotal() {
        double treatmentCost = treatment.getPrice();
        double tax = treatmentCost * taxRate;
        this.totalAmount = Math.round((treatmentCost + tax) * 100.0) / 100.0;
    }

    // Generate invoice
    public void generateInvoice() {
        System.out.println("Invoice for Appointment ID: " + appointment.getAppointmentId());
        System.out.println("Patient Name: " + appointment.getPatient().getName());
        System.out.println("Dermatologist: " + appointment.getDermatologist().getName());
        System.out.println("Treatment: " + treatment.getTreatmentType());
        System.out.println("Registration Fee: LKR " + appointment.getRegistrationFee());
        System.out.println("Treatment Cost: LKR " + treatment.getPrice());
        System.out.println("Total (with tax): LKR " + totalAmount);
    }
}
