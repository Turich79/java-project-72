package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.CheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.http.Context;
import kong.unirest.core.Unirest;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Element;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CheckController {
    public static void create(Context ctx) throws SQLException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(urlId);
        if (url.isPresent()) {
            try {
                var response = Unirest.get(url.get().getName()).asString();
                var statusCode = response.getStatus();
                var page = Jsoup.parse(response.getBody());
                String title = page.title();
                Element h1Element = page.selectFirst("h1");
                String h1 = h1Element == null ? "" : h1Element.text();
                Element descriptionElement = page.selectFirst("meta[name=description]");
                String description = descriptionElement == null ? "" : descriptionElement.attr("content");
                var createdAt = new Timestamp(System.currentTimeMillis());
                UrlCheck check = new UrlCheck(urlId, statusCode, h1, title, description, createdAt);
                CheckRepository.save(check);
            } catch (Exception e) {
                ctx.sessionAttribute("flash", "Failed to establish a connection to the site, "
                        + "verification is not possible.");
                ctx.sessionAttribute("flash-type", "danger");
            }
        } else {
            throw new SQLException("Url with this ID doesn't exist");
        }
        ctx.redirect(NamedRoutes.urlPath(urlId));
    }
}
