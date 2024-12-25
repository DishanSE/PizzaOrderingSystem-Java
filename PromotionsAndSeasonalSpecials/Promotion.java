package PromotionsAndSeasonalSpecials;

import java.time.LocalDate;

public class Promotion {
    private String name;
    private double discount;
    private LocalDate startDate;
    private LocalDate endDate;

    public Promotion() {}

    public Promotion(String name, double discount, LocalDate starDate, LocalDate endDate) {
        this.name = name;
        this.discount = discount;
        this.startDate = starDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setStartDate(LocalDate starDate) {
        this.startDate = starDate;
    }

    public void setEndDate(LocalDate enDate) {
        this.endDate = enDate;
    }
    
    public boolean isActive() {
        return LocalDate.now().isAfter(startDate) && LocalDate.now().isBefore(endDate);
    }
}
