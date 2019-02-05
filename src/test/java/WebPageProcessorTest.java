import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.WebPageServiceImpl;
import util.Page;

import static config.CircuitBreakerConfiguration.CIRCUIT_BREAKER_BUFFER_SIZE;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WebPageProcessorTest {

    private static final Logger LOG = LoggerFactory.getLogger(WebPageProcessorTest.class);

    private static final int NUMBER_OF_ATTEMPTS = 7;

    private WebPageProcessor webPageProcessor;

    @Spy
    private WebPageServiceImpl webPageService;

    @Before
    public void setUp(){
        webPageProcessor = new WebPageProcessor(webPageService);
    }

    @Test
    public void shouldProcessPageFromService(){

        HtmlPage htmlPage = webPageService.getPage(Page.ALIOR);

        assertThat(htmlPage.getWebResponse().getContentLength(), is(greaterThan(0L)));
    }

    @Test
    public void shouldProcessPage(){

        long pageSize = webPageProcessor.processPage(Page.ALIOR);

        assertThat(pageSize, is(greaterThan(0L)));
    }

    @Test
    public void shouldStopProcessingPageAfterNumberOfFailedAttempts(){
        when(webPageService.getPage(Page.ALIOR)).thenThrow(new RuntimeException("Service unavailable"));
        for (int i = 0; i < NUMBER_OF_ATTEMPTS ; i++) {
            try {
                webPageProcessor.processPage(Page.ALIOR);
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }

        verify(webPageService, times(CIRCUIT_BREAKER_BUFFER_SIZE)).getPage(anyString());
    }

}