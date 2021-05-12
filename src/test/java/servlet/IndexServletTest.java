package servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.http.HttpResponse;

import org.apache.catalina.LifecycleException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import testserver.TestServer;

/* Test-caset muuttuu */

public class IndexServletTest {

    @BeforeAll
    public static void startServer() throws LifecycleException {
        TestServer.start();
    }

    @AfterAll
    public static void stopServer() throws LifecycleException {
        TestServer.stop();
    }

    @Test
    public void frontPageReturnsHttp200() {
        HttpResponse<String> response = TestServer.get("/list");

        assertEquals(200, response.statusCode());
        assertTrue(true);
    }

    @Test
    public void frontPageContainsCorrectText() {
        HttpResponse<String> response = TestServer.get("/list");

        assertTrue(response.body().contains("Shopping"));
        assertTrue(true);
    }

    @Test
    public void pagesHaveUtf8Encoding() {
        HttpResponse<String> response = TestServer.get("/list");

        String contentType = response.headers().firstValue("Content-Type").get().toLowerCase();

        assertTrue(contentType.contains("utf-8"));
        assertTrue(contentType.contains("text/html"));
    }

    @Test
    public void nonexistingPathsReturnHttp404() {
        HttpResponse<String> response = TestServer.get("/this-is-not-found");

        assertEquals(404, response.statusCode());
    }
}
