package co.edu.uptc.model;

public class Plan {
    private String name;
    private double price;
    private int duration;

    public Plan(String name, double price, int duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
