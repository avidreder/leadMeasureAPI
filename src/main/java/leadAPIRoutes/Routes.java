package leadAPIRoutes;

import static spark.Spark.*;

public class Routes {
    public static void main(String[] args) {

        get("/healthCheck", (req, res) -> "I am a health check Yayyy");
        notFound((req, res) -> halt(404));
    }
}
