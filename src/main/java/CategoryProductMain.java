import manager.CategoryManager;
import manager.ProductManager;
import model.Category;
import model.Product;
import util.Commands;

import java.util.List;
import java.util.Scanner;


public class CategoryProductMain implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static CategoryManager categoryManager = new CategoryManager();
    private static ProductManager productManager = new ProductManager();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_CATEGORY:
                    addCategory();
                    break;
                case EDIT_CATEGORY_BY_ID:
                    editCategoryByID();
                    break;
                case DELETE_CATEGORY_BY_ID:
                    deleteCategoryByID();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case EDIT_PRODUCT_BY_ID:
                    editProductByID();
                    break;
                case DELETE_PRODUCT_BY_ID:
                    deleteProductByID();
                    break;
                case PRINT_SUM_OF_PRODUCTS:
                    printSumOfProducts();
                    break;
                case PRINT_MAX_OF_PRICE_PRODUCT:
                    printMaxOfPriceProduct();
                    break;
                case PRINT_MIN_OF_PRICE_PRODUCT:
                    printMinOfPriceProduct();
                    break;
                case PRINT_AVG_OF_PRICE_PRODUCT:
                    printAvgOfPriceProduct();
                    break;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }

    private static void printSumOfProducts() {
        int sum = productManager.getSumOfProductQuantities();
        System.out.println("Sum of product quantities: " + sum);

    }

    private static void printMaxOfPriceProduct() {
        int max = productManager.getProductsMaxPrice();
        System.out.println("Products max price: " + max);

    }

    private static void printMinOfPriceProduct() {
        int min = productManager.getProductsMinPrice();
        System.out.println("Products min price: " + min);

    }

    private static void printAvgOfPriceProduct() {
        int avg = productManager.getProductsAveragePrice();
        System.out.println("Average of price product: " + avg);

    }

    private static void deleteProductByID() {
        List<Product> all = productManager.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
        System.out.println("Please choose product id");
        int id = Integer.parseInt(scanner.nextLine());
        productManager.removeById(id);
        System.out.println("Product removed!");
    }

    private static void editProductByID() {
        List<Product> all = productManager.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
        System.out.println("Please choose product id");
        int id = Integer.parseInt(scanner.nextLine());
        if (productManager.getById(id) != null) {
            System.out.println("Please input product name,description,price,quantity,categoryID");
            String productStr = scanner.nextLine();
            String[] productData = productStr.split(",");
            Product product = new Product();
            product.setId(id);
            product.setName(productData[0]);
            product.setDescription(productData[1]);
            product.setPrice(Integer.parseInt(productData[2]));
            product.setQuantity(Integer.parseInt(productData[3]));
            Category category = categoryManager.getById(id);
            product.setCategory(category);
            productManager.edit(product);
            System.out.println("Product was edited!");
        } else {
            System.out.println("Product does not exists");
        }
    }

    private static void addProduct() {
        List<Category> all = categoryManager.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
        System.out.println("Please choose category id");
        int id = Integer.parseInt(scanner.nextLine());
        Category category = categoryManager.getById(id);
        if (category != null) {
            System.out.println("Please input product name,description,price,quantity");
            String productStr = scanner.nextLine();
            String[] productData = productStr.split(",");
            Product product = new Product();
            product.setCategory(category);
            product.setName(productData[0]);
            product.setDescription(productData[1]);
            product.setPrice(Integer.parseInt(productData[2]));
            product.setQuantity(Integer.parseInt(productData[3]));

            productManager.save(product);
        }
    }

    private static void deleteCategoryByID() {
        List<Category> all = categoryManager.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
        System.out.println("Please choose category id");
        int id = Integer.parseInt(scanner.nextLine());
        categoryManager.removeById(id);
        System.out.println("Category removed!");
    }

    private static void addCategory() {
        System.out.println("Please input category name");
        String categoryStr = scanner.nextLine();
        String[] categoryData = categoryStr.split(",");
        Category category = new Category();
        category.setName(categoryData[0]);
        categoryManager.save(category);
    }

    private static void editCategoryByID() {
        List<Category> all = categoryManager.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
        System.out.println("Please choose category id");
        int id = Integer.parseInt(scanner.nextLine());
        if (categoryManager.getById(id) != null) {
            System.out.println("Please input category name");
            String categoryStr = scanner.nextLine();
            String[] categoryData = categoryStr.split(",");
            Category category = new Category();
            category.setId(id);
            category.setName(categoryData[0]);

            categoryManager.edit(category);
            System.out.println("Category was edited!");
        } else {
            System.out.println("Category does not exists");
        }
    }


}
