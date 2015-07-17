package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        // return ok(index.render("Your new application is ready."));
        response().setContentType("text/html");
        return ok("<h1>Hello World!</h1>");
    }
}
