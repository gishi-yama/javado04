package com.example.page;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.example.WebPageTester;

public class HomePageTest extends WebPageTester {

  @Test
  @DirtiesContext
  public void Pageが表示される() {
    sut.startPage(HomePage.class);
    sut.assertRenderedPage(HomePage.class);
  }

}
