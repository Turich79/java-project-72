package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import hexlet.code.dto.flash.Flash;
import hexlet.code.utils.NamedRoutes;
import hexlet.code.dto.BasePage;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,18,18,18,19,19,19,19,20,20,20,22,22,26,26,26,26,26,26,26,26,26,29,29,29,29,29,29,29,29,29,35,35,35,42,42,42,5,6,7,7,7,7};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BasePage page, Content content, Flash flash) {
		jteOutput.writeContent("\r\n<!doctype html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"utf-8\" />\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\"><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\r\n    <title>Анализатор страниц</title>\r\n</head>\r\n<body>\r\n    ");
		if (flash != null) {
			jteOutput.writeContent("\r\n    <div class=\"alert alert-");
			jteOutput.setContext("div", "class");
			jteOutput.writeUserContent(flash.getType().name().toLowerCase());
			jteOutput.setContext("div", null);
			jteOutput.writeContent("\" role=\"alert\">\r\n        ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(page.getFlash().getText());
			jteOutput.writeContent("\r\n    </div>\r\n    ");
		}
		jteOutput.writeContent("\r\n    <div class=\"col-lg-8 mx-auto p-4 py-md-5\">\r\n        <header class=\"d-flex align-items-center pb-3 mb-5 border-bottom\">\r\n            <nav class=\"nav\">\r\n                <a");
		var __jte_html_attribute_0 = NamedRoutes.rootPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" class=\"nav-link d-flex align-items-center text-body-emphasis text-decoration-none\">\r\n                    <span class=\"fs-4\">Анализатор страниц</span>\r\n                </a>\r\n                <a");
		var __jte_html_attribute_1 = NamedRoutes.urlsPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" class=\"nav-link d-flex align-items-center text-body-emphasis text-decoration-none\">\r\n                    <span class=\"fs-4\">Страницы</span>\r\n                </a>\r\n            </nav>\r\n        </header>\r\n        <main>\r\n            ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\r\n        </main>\r\n        <footer class=\"pt-5 my-5 text-body-secondary border-top\">\r\n            <p>Created by <a href=\"https://ru.hexlet.io/\">Hexlet</a> · © 2024</p>\r\n        </footer>\r\n    </div>\r\n</body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BasePage page = (BasePage)params.getOrDefault("page", null);
		Content content = (Content)params.get("content");
		Flash flash = (Flash)params.getOrDefault("flash", null);
		render(jteOutput, jteHtmlInterceptor, page, content, flash);
	}
}
