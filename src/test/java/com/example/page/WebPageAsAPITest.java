package com.example.page;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import lombok.val;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.example.WebPageTester;
import com.example.service.ITableStorageService;

public class WebPageAsAPITest extends WebPageTester {
  
  @Override
  public void setUp() throws Exception {
    super.setUp();
    initMock();
    val mock = mock(ITableStorageService.class);
    doNothing().when(mock).addTemperature(anyInt());
    ctxMock.putBean(mock);
  }

  @Test
  @DirtiesContext
  public void APIのレスポンスのコンテントタイプが正しい() throws Exception {
    sut.startPage(WebPageAsAPI.class);
    val res = sut.getLastResponse();
    val actual = res.getContentType();
    assertThat(actual, is("application/json;charset=UTF-8"));
  }

  @Test
  @DirtiesContext
  public void APIを呼び出すとerrorが返る() throws Exception {
    sut.startPage(WebPageAsAPI.class);
    sut.assertResultPage("{ \"returning\" : \"NG\" }");
  }
  
  @Test
  @DirtiesContext
  public void 正しい値を渡してAPIを呼び出すとokが返る() throws Exception {
    
    PageParameters pp = new PageParameters();
    pp.set("value", "1");
    
    sut.startPage(WebPageAsAPI.class, pp);
    
    sut.assertResultPage("{ \"returning\" : \"OK\" }");
  }

}
