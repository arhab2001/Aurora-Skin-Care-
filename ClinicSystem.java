import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ClinicSystem {
    private ArrayList<Appointment> appointments;
    private ArrayList<Dermatologist> dermatologists;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public ClinicSystem() {
        appointments = new ArrayList<>();
        dermatologists = new ArrayList<>();
        scanner = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        initializeDermatologists();
    }

    // Initialize dermatologists
    private void initializeDermatologists() {
        dermatologists.add(new Dermatologist("Dr. Smith", "Monday", "10:00am - 01:00pm"));
        dermatologists.add(new Dermatologist("Dr. Lee", "Wednesday", "02:00pm - 05:00pm"));
        dermatologists.add(new Dermatologist("Dr. Kim", "Friday", "04:00pm - 08:00pm"));
        dermatologists.add(new Dermatologist("Dr. John", "Saturday", "09:00am - 01:00pm"));
    }

    // Book an appointment
    public void bookAppointment() {
        System.out.println("Enter Patient NIC:");
        String nic = scanner.next();
        System.out.println("Enter Patient Name:");
        String name = scanner.next();
        System.out.println("Enter Patient Email:");
        String email = scanner.next();
        System.out.println("Enter Patient Phone Number:");
        String phoneNumber = scanner.next();

        Patient patient = new Patient(nic, name, email, phoneNumber);

        System.out.println("Choose a Dermatologist:");
        for (int i = 0; i < dermatologists.size(); i++) {
            System.out.println((i + 1) + ". " + dermatologists.get(i).getName() + " (" + dermatologists.get(i).getAvailableDays() + ")");
        }
        int choice = scanner.nextInt();
        Dermatologist dermatologist = dermatologists.get(choice - 1);

        System.out.println("Enter Appointment Date (dd/MM/yyyy):");
        String dateInput = scanner.next();
        try {
            Date appointmentDate = dateFormat.parse(dateInput);
            Appointment appointment = new Appointment(patient, dermatologist, appointmentDate);
            appointments.add(appointment);

            System.out.println("Appointment booked successfully! Appointment ID: " + appointment.getAppointmentId());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

    // Search appointment by patient name or ID
    public void searchAppointment() {
        System.out.println("Enter Patient Name or Appointment ID:");
        String input = scanner.next();

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getName().equalsIgnoreCase(input) || Integer.toString(appointment.getAppointmentId()).equals(input)) {
                System.out.println("Appointment found for: " + appointment.getPatient().getName());
                System.out.println("Dermatologist: " + appointment.getDermatologist().getName());
                System.out.println("Date: " + dateFormat.format(appointment.getAppointmentDate()));
                return;
            }
        }
        System.out.println("No appointment found.");
    }

    // Update appointment
    public void updateAppointment() {
        System.out.println("Enter Appointment ID to update:");
        int id = scanner.nextInt();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == id) {
                System.out.println("Enter new Appointment Date (dd/MM/yyyy):");
                String newDateInput = scanner.next();
                try {
                    Date newDate = dateFormat.parse(newDateInput);

                    System.out.println("Choose new Dermatologist:");
                    for (int i = 0; i < dermatologists.size(); i++) {
                        System.out.println((i + 1) + ". " + dermatologists.get(i).getName());
                    }
                    int newChoice = scanner.nextInt();
                    Dermatologist newDermatologist = dermatologists.get(newChoice - 1);

                    appointment.updateAppointment(newDate, newDermatologist);
                    System.out.println("Appointment updated successfully!");
                    return;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please try again.");
                }
            }
        }
        System.out.println("No appointment found with ID: " + id);
    }

    // View appointments by date
    public void viewAppointmentsByDate() {
        System.out.println("Enter the date to filter appointments (dd/MM/yyyy):");
        String inputDate = scanner.next();
    
        boolean appointmentFound = false;
    
        try {
            Date filterDate = dateFormat.parse(inputDate);
            for (Appointment appointment : appointments) {
                if (dateFormat.format(appointment.getAppointmentDate()).equals(dateFormat.format(filterDate))) {
                    System.out.println("Appointment for " + appointment.getPatient().getName() + " with Dr. " + appointment.getDermatologist().getName());
                    appointmentFound = true;
                }
            }
    
            if (!appointmentFound) {
                System.out.println("No appointments found for the date " + inputDate);
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

    // Generate an invoice for a completed treatment
    public void generateInvoice() {
        System.out.println("Enter Appointment ID for invoice:");
        int id = scanner.nextInt();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == id) {
                System.out.println("Choose Treatment:");
                Treatment[] treatments = Treatment.getAvailableTreatments();
                for (int i = 0; i < treatments.length; i++) {
                    System.out.println((i + 1) + ". " + treatments[i].getTreatmentType() + " (LKR " + treatments[i].getPrice() + ")");
                }
                int treatmentChoice = scanner.nextInt();
                Treatment treatment = treatments[treatmentChoice - 1];

                Invoice invoice = new Invoice(appointment, treatment);
                invoice.generateInvoice();
                return;
            }
        }
        System.out.println("No appointment found with ID: " + id);
    }

    // Main menu
    public void start() {
        while (true) {
            System.out.println("Welcome to Aurora Skin Care Appointment System");
            System.out.println("1. Book Appointment");
            System.out.println("2. Update Appointment");
            System.out.println("3. View Appointments by Date");
            System.out.println("4. Search Appointment");
            System.out.println("5. Generate Invoice");
            System.out.println("6. Exit");
            System.out.println("------------------------------------------------------");
            System.out.print("Select The Number As Per Your Service Requirement  : ");
            int choice = scanner.nextInt();
            System.out.println("------------------------------------------------------");
            switch (choice) {
                case 1:
                    bookAppointment();
                    break;
                case 2:
                    updateAppointment();
                    break;
                case 3:
                    viewAppointmentsByDate();
                    break;
                case 4:
                    searchAppointment();
                    break;
                case 5:
                    generateInvoice();
                    break;
                case 6:
                    System.out.print("If You Want to Exit? (Yes/No): ");
                    scanner.nextLine();
                    String exitchoice = scanner.nextLine();
                    if (exitchoice.equalsIgnoreCase("Yes")) {  
                        System.exit(0);
                    } else {
                        start();
                    }
                
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please select again.");
            }
        }
    }

    public static void main(String[] args) {
        ClinicSystem clinicSystem = new ClinicSystem();
        clinicSystem.start();
    }
}
