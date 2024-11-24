package hexlet.code;

import hexlet.code.utils.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    static Javalin app;

    @BeforeEach
    void setUp() throws SQLException, IOException {
        app = App.getApp();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    public void testRoot() {
        JavalinTest.test(app, (server, client) -> {
            assertThat(client.get(NamedRoutes.rootPath()).code()).isEqualTo(200);
        });
    }

    @Test
    public void testUrls() {
        JavalinTest.test(app, (server, client) -> {
            assertThat(client.get(NamedRoutes.urlsPath()).code()).isEqualTo(200);
        });
    }

    @Test
    public void testUrl() {
        JavalinTest.test(app, (server, client) -> {
            try (var postResponse = client.post(NamedRoutes.urlsPath(), "url=https://afisha.yandex.ru/")) {
                assertThat(postResponse.code()).isEqualTo(200);

                var getResponse2 = client.get(NamedRoutes.urlPath(1L));
                assertThat(getResponse2.code()).isEqualTo(200);
                assertThat(getResponse2.body().string()).contains("afisha.yandex.ru");
            }
        });
    }

    @Test
    public void testPostWithCorrectURL1() {
        JavalinTest.test(app, (server, client) -> {
            try (var postResponse = client.post(NamedRoutes.urlsPath(), "url=https://afisha.yandex.ru/")) {
                assertThat(postResponse.code()).isEqualTo(200);

                var getResponse1 = client.get(NamedRoutes.urlsPath());
                assertThat(getResponse1.code()).isEqualTo(200);
                assertThat(getResponse1.body().string()).contains("afisha.yandex.ru");

                var getResponse2 = client.get(NamedRoutes.urlPath(1L));
                assertThat(getResponse2.code()).isEqualTo(200);
                assertThat(getResponse2.body().string()).contains("afisha.yandex.ru");
            }
        });
    }

    @Test
    public void testPostWithCorrectURL2() {
        JavalinTest.test(app, (server, client) -> {
            try (var postResponse = client.post(NamedRoutes.urlsPath(),
                    "url=https://some-domain.org/example/path")) {
                assertThat(postResponse.code()).isEqualTo(200);

                var getResponse1 = client.get(NamedRoutes.urlsPath());
                assertThat(getResponse1.code()).isEqualTo(200);
                assertThat(getResponse1.body().string()).contains("https://some-domain.org");

                var getResponse2 = client.get(NamedRoutes.urlPath(1L));
                assertThat(getResponse2.code()).isEqualTo(200);
                assertThat(getResponse2.body().string()).contains("https://some-domain.org");
            }
        });
    }

    @Test
    public void testPostWithCorrectURL3() {
        JavalinTest.test(app, (server, client) -> {
            try (var postResponse = client.post(NamedRoutes.urlsPath(),
                    "url=https://some-domain.org:8080/example/path")) {
                assertThat(postResponse.code()).isEqualTo(200);

                var getResponse1 = client.get(NamedRoutes.urlsPath());
                assertThat(getResponse1.code()).isEqualTo(200);
                assertThat(getResponse1.body().string()).contains("https://some-domain.org:8080");

                var getResponse2 = client.get(NamedRoutes.urlPath(1L));
                assertThat(getResponse2.code()).isEqualTo(200);
                assertThat(getResponse2.body().string()).contains("https://some-domain.org:8080");
            }
        });
    }

    @Test
    public void testPostWIthIncorrectURL() {
        JavalinTest.test(app, (server, client) -> {
            try (var postResponse = client.post(NamedRoutes.urlsPath(), "url=incorrectURL")) {
                assertThat(postResponse.code()).isEqualTo(200);

                var getResponse1 = client.get(NamedRoutes.urlsPath());
                assertThat(getResponse1.body().string()).doesNotContain("incorrectURL");

                var getResponse2 = client.get(NamedRoutes.urlPath(1L));
                System.out.println(getResponse2.body().string());
                assertThat(getResponse2.code()).isEqualTo(404);
            }
        });
    }

    @AfterEach
    void tearDown() {
        app.stop();
    }
}
