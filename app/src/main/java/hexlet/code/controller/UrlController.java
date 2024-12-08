package hexlet.code.controller;

//import hexlet.code.dto.flash.Flash;
//import hexlet.code.dto.flash.FlashType;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.CheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.net.MalformedURLException;
import java.net.URI;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class UrlController {
    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getUrls();
        Map<Long, UrlCheck> urlChecks = CheckRepository.findLatestChecks();
        var page = new UrlsPage(urls, urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) throws SQLException, NotFoundResponse {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id);
        var checks = CheckRepository.getUrlChecks(id);
        if (url.isPresent()) {
            var page = new UrlPage(url.get(), checks);
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            page.setFlashType(ctx.consumeSessionAttribute("flashType"));
            ctx.render("urls/show.jte", Collections.singletonMap("page", page));
        } else {
            throw new NotFoundResponse("Url not found");
        }
    }

    public static void create(Context ctx) throws SQLException {
        var websiteLink = ctx.formParam("url");
        try {
            var url = new Url(parseUri(websiteLink).orElseThrow(MalformedURLException::new));
            url.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            UrlRepository.save(url);
            ctx.sessionAttribute("flash", "Страница успешно проверена");
            ctx.sessionAttribute("flash-type", "success");
        } catch (MalformedURLException e) {
            ctx.sessionAttribute("flash", "Некорректный адрес");
            ctx.sessionAttribute("flash-type", "danger");
        } catch (SQLDataException e) {
            ctx.sessionAttribute("flash", e.getMessage());
            ctx.sessionAttribute("flash-type", "danger");
        }
        ctx.redirect(NamedRoutes.rootPath());
    }

    private static Optional<String> parseUri(String path) throws MalformedURLException {
        try {
            var uri = URI.create(path);
            var url = uri.toURL();
            if (url.getProtocol() == null || url.getAuthority() == null) {
                throw new IllegalArgumentException();
            }
            return Optional.of(url.getProtocol() + "://" + url.getAuthority());
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
