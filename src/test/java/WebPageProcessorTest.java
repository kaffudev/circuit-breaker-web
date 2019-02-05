import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import service.WebPageServiceImpl;
import util.Page;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WebPageProcessorTest {

    private WebPageProcessor webPageProcessor;

    @Spy
    private WebPageServiceImpl webPageService;

    @Before
    public void setUp(){
        webPageProcessor = new WebPageProcessor(webPageService);
    }

    @Test
    public void shouldProcessPage(){

        HtmlPage htmlPage = webPageService.getPage(Page.ALIOR);

        assertThat(htmlPage.getWebResponse().getContentLength(), is(greaterThan(0L)));
    }

}