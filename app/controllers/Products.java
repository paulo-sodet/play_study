package controllers;

import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.products.details;

import java.util.List;

/**
 * Created by sodet-user on 17/07/15.
 */
public class Products extends Controller{

    private static final Form<Product> productForm = Form.form(Product.class);

    public static Result list() {
        List<Product> products = Product.findAll();
        return ok(views.html.products.list.render(products));
    }

    public static Result newProduct() {
        return ok(views.html.products.details.render(productForm));
    }

    public static Result details(String ean){
        final Product product = Product.findByEan(ean);
        if (product == null){
            return notFound(String.format("Product %s doesnot exist.", ean));
        }

        Form<Product> filledForm = productForm.fill(product);
        return ok(details.render(filledForm));
    }


    public static Result save() {
        Form<Product> boundForm  = productForm.bindFromRequest();
        if (boundForm.hasErrors()){
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }

        Product product = boundForm.get();
        product.save();
        flash("sucess", String.format("Saved product %s", product));
        return redirect(routes.Products.list());

    }

    public static Result delete(String ean){
        final Product product = Product.findByEan(ean);
        if (product == null){
            return notFound(String.format("Not found"));
        }

        Product.remove(product);
        return redirect(routes.Products.list());
    }
}
