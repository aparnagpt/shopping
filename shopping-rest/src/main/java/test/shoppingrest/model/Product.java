package test.shoppingrest.model;

public class Product {
    private int id;
    private String name;
    private int amount;
    private String description;

    public Product() {
    }

    public Product(int id, String name, int amount, String description) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.description = description;
    }

    public Product(int id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
