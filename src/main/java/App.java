import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

import models.Post;
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

    get("/", (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        ArrayList<Post> posts = Post.getAll();
        model.put("posts", posts);
        return new ModelAndView(model, "index.hbs");
    }, new HandlebarsTemplateEngine());

    post("/posts/new", (request, response) -> { //URL to make new post on POST route
        Map<String, Object> model = new HashMap<String, Object>();
        String content = request.queryParams("content");
        Post newPost = new Post(content);
        return new ModelAndView(model, "success.hbs");
    }, new HandlebarsTemplateEngine());
}
}