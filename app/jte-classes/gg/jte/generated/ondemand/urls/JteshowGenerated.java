package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.utils.NamedRoutes;
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,5,5,8,8,11,11,11,12,12,12,15,15,15,15,15,15,15,15,15,21,21,23,23,36,36,37,37,39,39,39,40,40,40,41,41,41,42,42,42,43,43,43,44,44,44,46,46,47,47,51,51,51,51,51,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <main>\r\n            <div>\r\n                <h1>Website: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\r\n                <p>Created at: ");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(page.getUrl().getCreatedAtFormatted());
				jteOutput.writeContent("</p>\r\n            </div>\r\n            <div class=\"d-flex gap-2 justify-content-left py-5\">\r\n                <form");
				var __jte_html_attribute_0 = NamedRoutes.checkPath(page.getUrl().getId());
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\">\r\n                    <button class=\"btn btn-primary d-inline-flex align-items-center\" type=\"submit\">\r\n                        Проверить\r\n                    </button>\r\n                </form>\r\n            </div>\r\n            ");
				if (page.getChecks().isEmpty()) {
					jteOutput.writeContent("\r\n                <p>Еще не стартовали проверки.</p>\r\n            ");
				}
				jteOutput.writeContent("\r\n            <table class=\"table table-striped\">\r\n                <thead>\r\n                <tr>\r\n                    <th scope=\"col\">ID</th>\r\n                    <th scope=\"col\">Status code</th>\r\n                    <th scope=\"col\">H1</th>\r\n                    <th scope=\"col\">Title</th>\r\n                    <th scope=\"col\">Description</th>\r\n                    <th scope=\"col\">Check started</th>\r\n                </tr>\r\n                </thead>\r\n                <tbody>\r\n                ");
				if (!page.getChecks().isEmpty()) {
					jteOutput.writeContent("\r\n                    ");
					for (var check : page.getChecks()) {
						jteOutput.writeContent("\r\n                        <tr>\r\n                            <th scope=\"row\">");
						jteOutput.setContext("th", null);
						jteOutput.writeUserContent(check.getId());
						jteOutput.writeContent("</th>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(check.getStatusCode());
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(check.getH1());
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(check.getTitle());
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(check.getDescription());
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(check.getCreatedAtFormatted());
						jteOutput.writeContent("</td>\r\n                        </tr>\r\n                    ");
					}
					jteOutput.writeContent("\r\n                ");
				}
				jteOutput.writeContent("\r\n                </tbody>\r\n            </table>\r\n        </main>\r\n    ");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
