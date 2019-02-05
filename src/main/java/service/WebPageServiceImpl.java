package service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class WebPageServiceImpl implements WebPageService {

    @Override
    public HtmlPage getPage(String url) {
        try(WebClient webClient = new WebClient()) {
            webClientConfiguration(webClient);
            try {
                return webClient.getPage(url);
            } catch (Exception e) {
                throw new RuntimeException("Could not get page " + url, e);
            }
        }
    }

    private void webClientConfiguration(WebClient webClient) {
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
    }
}
