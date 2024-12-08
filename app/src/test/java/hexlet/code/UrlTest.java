package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.repository.CheckRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public final class UrlTest {
    private static Javalin app;
    private static final int STATUS_SUCCESS = 200;
    private static MockWebServer mockWebServer;
    private String baseUrl;

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        baseUrl = mockWebServer.url("/").toString();
        app = App.getApp();
    }

    @BeforeAll
    public static void startMockWebServer() throws IOException {
        String html = Files.readString(Paths.get("src/test/resources/example.html").toAbsolutePath()).trim();
        mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setResponseCode(STATUS_SUCCESS).setBody(html));
        mockWebServer.start();
    }

    @Test
    void testUrlsPage() throws Exception {
        Url url = new Url(baseUrl);
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(STATUS_SUCCESS);
            assertThat(response.body().string()).contains(baseUrl);
        });
    }

    @Test
    public void testChecks() throws SQLException {
        Url url = new Url(baseUrl);
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            Url urlTest = UrlRepository.search(url.getName())
                    .orElseThrow(() -> new NotFoundResponse("Url not found"));
            var response = client.post(NamedRoutes.checkPath(urlTest.getId()));
            var optionalCheck = CheckRepository.getLastCheck(urlTest.getId());
            var checks = optionalCheck.orElseThrow(() -> new NotFoundResponse("UrlCheck not found"));

            assertThat(response.code()).isEqualTo(STATUS_SUCCESS);
            assertThat(response.body().string()).contains(String.valueOf(checks.getUrlId()));

            assertThat(checks.getTitle()).isEqualTo("Проверочная страница");
            assertThat(checks.getH1()).isEqualTo("H1 Проверочная страница");
            assertThat(checks.getDescription()).isEqualTo("Эта страничка для тестов");

            var responseUrls = client.get("/urls");
            assertThat(responseUrls.code()).isEqualTo(STATUS_SUCCESS);
            assertThat(responseUrls.body().string()).contains(String.valueOf(checks.getStatusCode()));
        });
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}
