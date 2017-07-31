package leadAPIRoutes;

import static spark.Spark.*;

public class Routes {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
        get("/healthCheck", (req, res) -> "I am a health check Yayyy");
    }
}
