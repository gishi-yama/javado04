package com.example;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebInitializer.class, WicketApplication.class})
@TestPropertySource("classpath:application.properties")
public abstract class WebPageTester {

  @Autowired
  protected WicketApplication wicketApplication;

  protected WicketTester sut;

  protected ApplicationContextMock ctxMock;

  @Before
  public void setUp() throws Exception {
    sut = new WicketTester(wicketApplication);
  }

  public void initMock() {
    ctxMock = new ApplicationContextMock();
    sut.getApplication().getComponentInstantiationListeners()
        .add(new SpringComponentInjector(sut.getApplication(), ctxMock));
  }

}
