package application;
import java.sql.Date;

public class ImportCar {
    private String importId;
    private Date importDate;
    private double importCost;
    private String origin;

    public ImportCar() {
        // Default constructor
    }

    public ImportCar(String importId, Date importDate, double importCost, String origin) {
        this.importId = importId;
        this.importDate = importDate;
        this.importCost = importCost;
        this.origin = origin;
    }

    // Getters and Setters
    public String getImportId() {
        return importId;
    }

    public void setImportId(String importId) {
        this.importId = importId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public double getImportCost() {
        return importCost;
    }

    public void setImportCost(double importCost) {
        this.importCost = importCost;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "ImportCar{" +
                "importId='" + importId + '\'' +
                ", importDate=" + importDate +
                ", importCost=" + importCost +
                ", origin='" + origin + '\'' +
                '}';
    }
}
