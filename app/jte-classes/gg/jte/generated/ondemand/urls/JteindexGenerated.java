package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.utils.NamedRoutes;
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,6,6,10,10,12,12,24,24,25,25,27,27,27,28,28,28,28,28,28,28,28,28,28,28,28,29,29,29,30,30,31,31,31,32,32,32,33,33,36,36,38,38,39,39,43,43,43,43,43,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, null, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <h1 class=\"text-body-emphasis\">Websites</h1>\r\n        <p class=\"fs-5 col-md-8\">Проверка страниц может занять немного времени.</p>\r\n        <div class=\"mb-5\">\r\n            ");
				if (page.getUrls().isEmpty()) {
					jteOutput.writeContent("\r\n            <p>No added websites yet.</p>\r\n            ");
				}
				jteOutput.writeContent("\r\n            <table class=\"table table-striped\">\r\n                <thead>\r\n                <tr>\r\n                    <th scope=\"col\">ID</th>\r\n                    <th scope=\"col\">URL</th>\r\n                    <th scope=\"col\">Added at</th>\r\n                    <th scope=\"col\">Last check</th>\r\n                    <th scope=\"col\">Status code</th>\r\n                </tr>\r\n                </thead>\r\n                <tbody>\r\n                ");
				if (!page.getUrls().isEmpty()) {
					jteOutput.writeContent("\r\n                ");
					for (var url : page.getUrls()) {
						jteOutput.writeContent("\r\n                    <tr>\r\n                        <th scope=\"row\">");
						jteOutput.setContext("th", null);
						jteOutput.writeUserContent(url.getId());
						jteOutput.writeContent("</th>\r\n                        <td><a");
						var __jte_html_attribute_0 = NamedRoutes.urlPath(url.getId());
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
							jteOutput.writeContent(" href=\"");
							jteOutput.setContext("a", "href");
							jteOutput.writeUserContent(__jte_html_attribute_0);
							jteOutput.setContext("a", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(url.getName());
						jteOutput.writeContent("</a></td>\r\n                        <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getCreatedAtFormatted());
						jteOutput.writeContent("</td>\r\n                        ");
						if (url.getLastCheck().isPresent()) {
							jteOutput.writeContent("\r\n                            <td>");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(url.getLastCheck().get().getCreatedAtFormatted());
							jteOutput.writeContent("</td>\r\n                            <td>");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(url.getLastCheck().get().getStatusCode());
							jteOutput.writeContent("</td>\r\n                        ");
						} else {
							jteOutput.writeContent("\r\n                            <td></td>\r\n                            <td></td>\r\n                        ");
						}
						jteOutput.writeContent("\r\n                    </tr>\r\n                ");
					}
					jteOutput.writeContent("\r\n                ");
				}
				jteOutput.writeContent("\r\n                </tbody>\r\n            </table>\r\n        </div>\r\n    ");
			}
		}, null);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
