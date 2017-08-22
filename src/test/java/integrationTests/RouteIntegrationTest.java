package integrationTests;
import net.avidreder.lead_measure.Application;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import spark.utils.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import spark.Spark;

public class RouteIntegrationTest {

    @BeforeClass
    public static void beforeClass() {
        Application.main(null);
        Spark.awaitInitialization();
    }

    @Test
    public void helloRouteReturns200Response() {
        TestResponse res = request("GET", "/hello");
        assertEquals(200, res.status);
    }

    @Test
    public void healthCheckRouteReturns200Response() {
        TestResponse res = request("GET", "/healthCheck");
        assertEquals(200, res.status);
    }

    @Test
    public void nonExistentReturns404Response() {
        TestResponse res = request("GET", "/badRoute");
        assertEquals(404, res.status);
    }

    @AfterClass
    public static void tearDown() {
        Spark.stop();
    }

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            int status = connection.getResponseCode();
            String body = null;
            if (status != 404) {
                body = IOUtils.toString(connection.getInputStream());
            }
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
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
    }
}
