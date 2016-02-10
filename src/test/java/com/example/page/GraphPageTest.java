package com.example.page;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.example.WebPageTester;

public class GraphPageTest extends WebPageTester {

  @Test
  @DirtiesContext
  public void Pageが表示される() {
    sut.startPage(GraphPage.class);
    sut.assertRenderedPage(GraphPage.class);
  }

}
