public class Dermatologist {
    private String name;
    private String availableDays;
    private String availableTime;

    public Dermatologist(String name, String availableDays, String availableTime) {
        this.name = name;
        this.availableDays = availableDays;
        this.availableTime = availableTime;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAvailableDays() {
        return availableDays;
    }

    public String getAvailableTime() {
        return availableTime;
    }
}
