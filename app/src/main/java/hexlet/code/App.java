package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import hexlet.code.controller.CheckController;
import hexlet.code.controller.RootController;
import hexlet.code.controller.UrlController;
import hexlet.code.repository.BaseRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class App {
    public static Javalin getApp() throws SQLException, IOException {
        Logger logger = LoggerFactory.getLogger(App.class);
        //////////
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getDatabaseUrl());

        var dataSource = new HikariDataSource(hikariConfig);
        String sql = loadDatabaseSchema("schema.sql");

        logger.info(sql);
        try (var connection = dataSource.getConnection();
            var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        //////////
//        var hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(getURL());
//        authentication(hikariConfig);
//        var dataSource = new HikariDataSource(hikariConfig);
//        var sql = loadDatabaseSchema("schema.sql");
//
//        try (var connection = dataSource.getConnection();
//             var statement = connection.createStatement()) {
//            statement.execute(sql);
//        }
        ///////////
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        app.get(NamedRoutes.rootPath(), RootController::index);
        app.get(NamedRoutes.urlsPath(), UrlController::index);
        app.get(NamedRoutes.urlPath(), UrlController::show);
        app.post(NamedRoutes.urlsPath(), UrlController::create);
        app.post(NamedRoutes.checksPath(), CheckController::create);
        logger.info("Logger started");

        return app;
    }

    public static void main(String[] args) throws SQLException, IOException {
        Javalin app = getApp();
        app.start(getPort());
    }

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    private static String getDatabaseUrl() {
        return System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project");
    }

//    private static String getURL() {
////        String localURL = "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;";
////        String url = System.getenv().getOrDefault("JDBC_DATABASE_URL", localURL);
////        return url;
////        return System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");
////        return "jdbc:postgresql://db:5432/postgres";
//        ////031224 делаю заглушку для прохождения тестов
//        return "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;";
//    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

//    private static void authentication(HikariConfig config) {
////        String username = System.getenv().getOrDefault("JDBC_DATABASE_USERNAME", "postgres");
////        String password = System.getenv().getOrDefault("JDBC_DATABASE_PASSWORD", "password");
//        String username = System.getenv("JDBC_DATABASE_USERNAME");
//        String password = System.getenv("JDBC_DATABASE_PASSWORD");
//        config.setUsername(username);
//        config.setPassword(password);
//    }

    private static String loadDatabaseSchema(String fileName) throws IOException {
//        var name = System.getenv("JDBC_DATABASE_URL") == null ? "h2.sql" : "postgre.sql";
//        var inputStream = App.class.getClassLoader().getResourceAsStream(name);
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
//            return reader.lines().collect(Collectors.joining("\n"));
//        }
        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
