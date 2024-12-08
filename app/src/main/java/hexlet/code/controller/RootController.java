package hexlet.code.controller;
import hexlet.code.dto.MainPage;
//import hexlet.code.dto.flash.Flash;
import io.javalin.http.Context;

import java.util.Collections;

public class RootController {
    public static void index(Context ctx) {
        var page = new MainPage();
//        Flash flash = ctx.consumeSessionAttribute("flash");
//        page.setFlash(flash);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }
}
