package com.example.page;

import static org.mockito.Mockito.*;
import lombok.val;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;

import com.example.WebPageTester;
import com.example.service.IBarService;

@RunWith(Enclosed.class)
public class ServicePageTest {

  public static class モックありテスト extends WebPageTester {

    @Override
    public void setUp() throws Exception {
      super.setUp();
//      val applicationContextMock = new ApplicationContextMock();
//      sut.getApplication().getComponentInstantiationListeners()
//        .add(new SpringComponentInjector(sut.getApplication(), applicationContextMock));
      initMock();
      val mock = mock(IBarService.class);
      when(mock.fetchMessage()).thenReturn("it is mocked!");
      ctxMock.putBean("barService", mock);
    }

    @Test
    @DirtiesContext
    public void Pageが表示される() {
      sut.startPage(ServicePage.class);
      sut.assertRenderedPage(ServicePage.class);
    }
    

    @Test
    @DirtiesContext
    public void Labelが表示される() {
      sut.startPage(ServicePage.class);
      sut.assertLabel("label", "it is mocked!");
    }

  }

  public static class モックなしテスト extends WebPageTester {

    @Test
    @DirtiesContext
    public void Pageが表示される() {
      sut.startPage(ServicePage.class);
      sut.assertRenderedPage(ServicePage.class);
    }
    
    @Test
    @DirtiesContext
    public void Labelが表示される() {
      sut.startPage(ServicePage.class);
      sut.assertLabel("label", "Hello, Spring Boot + Wicket!");
    }
  }

}
