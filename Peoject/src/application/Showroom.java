package application;

public class Showroom {
    private int licenseNumber;
    private String phone;
    private String name;
    private String location;
    private String ownership;

    public Showroom(int licenseNumber, String phone, String name, String location, String ownership) {
        this.licenseNumber = licenseNumber;
        this.phone = phone;
        this.name = name;
        this.location = location;
        this.ownership = ownership;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    @Override
    public String toString() {
        return "Showroom{" +
                "licenseNumber=" + licenseNumber +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", ownership='" + ownership + '\'' +
                '}';
    }
}