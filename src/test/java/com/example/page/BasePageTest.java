package com.example.page;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.example.WebPageTester;

public class BasePageTest extends WebPageTester {
  
  @Test
  @DirtiesContext
  public void Pageが表示される() {
    sut.startPage(new BasePage() {
      private static final long serialVersionUID = 1L;

      @Override
      public String getPageName() {
        return "Hoge";
      }
    });
    sut.assertRenderedPage(BasePage.class);
  }

}
