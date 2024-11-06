import java.util.Date;

public class Appointment {
    private static int appointmentIdCounter = 0;
    private int appointmentId;
    private Patient patient;
    private Dermatologist dermatologist;
    private Date appointmentDate;
    private double registrationFee = 500.00;

    public Appointment(Patient patient, Dermatologist dermatologist, Date appointmentDate) {
        this.appointmentId = ++appointmentIdCounter;
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.appointmentDate = appointmentDate;
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    // Method to update appointment
    public void updateAppointment(Date newDate, Dermatologist newDermatologist) {
        this.appointmentDate = newDate;
        this.dermatologist = newDermatologist;
    }
}
