package hexlet.code;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

public class App {
    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        //app.get(NamedRoutes.rootPath(), SessionsController::index);
        //app.get(NamedRoutes.buildSessionPath(), SessionsController::build);
        //app.post(NamedRoutes.loginPath(), SessionsController::create);
        //app.post(NamedRoutes.logoutPath(), SessionsController::destroy);

        app.get("/", ctx -> ctx.result("Hello World"));

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(getPort());
        System.out.println("Hello");
    }
}
