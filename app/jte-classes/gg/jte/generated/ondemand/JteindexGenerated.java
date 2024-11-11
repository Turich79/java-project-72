package gg.jte.generated.ondemand;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {23,23,23,23,23,23,23,23,23,23,23};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<html lang=\"en\">\r\n    <head>\r\n    <meta charset=\"utf-8\" />\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n    <title>Hello Hexlet!</title>\r\n    </head>\r\n    <body>\r\n    <div class=\"col-lg-8 mx-auto p-4 py-md-5\">\r\n        <main>\r\n        <H1>Анализатор страниц</H1>\r\n                <H3>Бесплатно проверяйте сайты на SEO пригодность </H3>\r\n                <main>\r\n                        <a href=\"/articles\">Все статьи</a>\r\n\r\n                        <form action=\"/users\" method=\"get\">\r\n                                    <input type=\"search\" name=\"term\" value=\"\"/>\r\n                                    <input type=\"submit\" value=\"Проверить\" />\r\n                                </form>\r\n                    </main>\r\n        </main>\r\n    </div>\r\n    </body>\r\n</html>\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
