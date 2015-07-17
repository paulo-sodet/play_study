package models;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.Constraints;

/**
 * Created by sodet-user on 17/07/15.
 */
public class Product {
    @Constraints.Required
    public String ean;
    @Constraints.Required
    public String name;
    public String description;

    private static List<Product> products;

    // database required
    public Product() {}

    public Product(String ean, String name, String desc){
        this.ean = ean;
        this.name = name;
        this.description = desc;
    }

    public String toString(){
        return String.format("%s - %s", ean, name);
    }

    //Do not be alarmed, this is just for testing purposes!

    static {
        products = new ArrayList<Product>();
        products.add(new Product("1", "PaperClips 1", "Paperclips description 1"));
        products.add(new Product("2", "PaperClips 2", "Paperclips description 2"));
        products.add(new Product("3", "PaperClips 3", "Paperclips description 3"));
        products.add(new Product("4", "PaperClips 4", "Paperclips description 4"));
        products.add(new Product("5", "PaperClips 5", "Paperclips description 5"));
    }

    public static List<Product> findAll() {
        return new ArrayList<Product>(products);
    }

    public static Product findByEan(String ean){
        for (Product candidate : products) {
            if (candidate.ean.equals(ean)) {
                return candidate;
            }
        }
        return null;
    }

    public static List<Product> findByName(String term) {
        final List<Product> results = new ArrayList<Product>();
        for (Product candidate : products){
            if (candidate.name.toLowerCase().contains(term.toLowerCase())){
                results.add(candidate);
            }
        }
        return results;
    }

    public static boolean remove(Product product){
        return products.remove(product);
    }

    public void save(){
        products.remove(findByEan(this.ean));
        products.add(this);
    }

}
