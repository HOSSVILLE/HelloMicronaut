package org.hoss.learn.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

// must put the git.properties in the resources directory of the test package
public class InfoEndpointTest {

    private static EmbeddedServer server;
    private static HttpClient client;

    @BeforeClass
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server
                .getApplicationContext()
                .createBean(HttpClient.class, server.getURL());
    }

    @AfterClass
    public static void stopServer() {
        if (server!=null) {
            server.stop();
        }
        if (client !=null) {
            client.stop();
        }
    }

    @Test
    public void testGitComitInfoAppearsInJson() {

        HttpRequest request = HttpRequest.GET("/");

        HttpResponse<String> rsp = client.toBlocking().exchange(request, String.class);

        assertEquals(rsp.status().getCode(), 200);

        String message = rsp.body();

        then:
        assertNotNull(message);
        assertEquals("Hello World from Micronaut!", message);
        
    }

}
