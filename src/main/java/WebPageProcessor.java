import com.gargoylesoftware.htmlunit.html.HtmlPage;
import config.CircuitBreakerConfiguration;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.WebPageService;

public class WebPageProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(WebPageProcessor.class);

    private WebPageService webPageService;
    private CircuitBreaker circuitBreaker;

    public WebPageProcessor(WebPageService webPageService) {
        this.webPageService = webPageService;
        this.circuitBreaker = CircuitBreakerConfiguration.createCircuitBreaker();
    }

    public long processPage(String url){
        HtmlPage page = CircuitBreaker.decorateFunction(circuitBreaker, this::downloadPage).apply(url);
        return getPageSize(page);
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
