import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.WebPageService;

public class WebPageProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(WebPageProcessor.class);

    private WebPageService webPageService;

    public WebPageProcessor(WebPageService webPageService) {
        this.webPageService = webPageService;
    }

    public HtmlPage downloadPage(String pageUrl) {
        LOG.info("Trying to download page {}", pageUrl);
        return webPageService.getPage(pageUrl);
    }

    private long getPageSize(HtmlPage page){
        LOG.info("Computing page size ...");
        return page.getWebResponse().getContentLength();
    }
}
