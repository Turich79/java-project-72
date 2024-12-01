package gg.jte.generated.ondemand;
import hexlet.code.utils.NamedRoutes;
import hexlet.code.dto.MainPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,5,5,9,9,13,13,13,13,13,13,13,13,13,18,18,18,18,18,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, MainPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <h1 class=\"text-body-emphasis\">Бесплатно проверяйте сайты на SEO пригодность</h1>\r\n        <p class=\"fs-5 col-md-8\">Легко и быстро. Просто добавляйте ссылки.</p>\r\n        <div class=\"mb-5\">\r\n            <form");
				var __jte_html_attribute_0 = NamedRoutes.urlsPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\" class=\"d-flex\">\r\n                <input class=\"form-control me-2\" required type=\"search\" placeholder=\"https://example.com\" name=\"url\" aria-label=\"Check\">\r\n                <button class=\"btn btn-outline-success\" type=\"submit\">Проверить</button>\r\n            </form>\r\n        </div>\r\n    ");
			}
		}, page.getFlash());
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		MainPage page = (MainPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
