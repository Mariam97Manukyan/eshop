package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor


public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    @NonNull
    private Category category;

    public Product() {
    }

}
