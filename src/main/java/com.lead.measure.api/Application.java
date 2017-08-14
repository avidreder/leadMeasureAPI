package com.lead.measure.api;
import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

public class Application {

    // Declare dependencies
    // public static BookDao bookDao;
    // public static UserDao userDao;

    public static void main(String[] args) {

        // Instantiate your dependencies
        // bookDao = new BookDao();
        // userDao = new UserDao();

        // Configure Spark
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        enableDebugScreen();

        // Set up before-filters (called before each get/post)
        // before("*",                  Filters.addTrailingSlashes);
        // before("*",                  Filters.handleLocaleChange);

        // Set up routes
        get("/hello", (req, res) -> "Hello world");
        get("/healthCheck", (req, res) -> "I am a health check Yayyy");
        notFound((req, res) -> {
            res.status(404);
            return "Not Found";
        });

        //Set up after-filters (called after each get/post)
        // after("*",                   Filters.addGzipHeader);

    }

}
