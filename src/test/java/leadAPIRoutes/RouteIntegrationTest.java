package leadAPIRoutes;

import leadAPIRoutes.Routes;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import spark.utils.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Category;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static spark.Spark.after;
import static spark.Spark.before;
import spark.Spark;

public class RouteIntegrationTest {
    private static int PORT = 4567;

    @AfterClass
    public static void tearDown() {
        Spark.stop();
    }

//    @After
//    public void clearBooks() {
//        Books.books.clear();
//    }

    @BeforeClass
    public static void beforeClass() {
//        before((request, response) -> {
//            response.header("FOZ", "BAZ");
//        });

        Routes.main(null);

//        after((request, response) -> {
//            response.header("FOO", "BAR");
//        });

//        Spark.awaitInitialization();
    }

    @Test
    public void testHelloRoute() {
        TestResponse res = request("GET", "/hello");
        System.out.print(res.status);
        assertEquals(200, res.status);
    }

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            System.out.println(url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
//            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

//        public Map<String,String> json() {
//            return new Gson().fromJson(body, HashMap.class);
//        }
    }
}


