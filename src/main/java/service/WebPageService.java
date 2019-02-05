package service;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface WebPageService {
    HtmlPage getPage(String url);
}
