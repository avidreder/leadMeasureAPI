package leadAPIRoutes;

import static spark.Spark.*;

public class Routes {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello world");
        get("/healthCheck", (req, res) -> "I am a health check Yayyy");
        notFound((req, res) -> {
            res.status(404);
            return "Not Found";
        });
    }
}
