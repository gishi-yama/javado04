package com.example.page;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.val;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.example.WebPageTester;
import com.example.service.ITableStorageService;

public class GraphPageTest extends WebPageTester {

  @Override
  public void setUp() throws Exception {
    super.setUp();
    initMock();
    val mock = mock(ITableStorageService.class);
    val temperatures = IntStream.range(0, 20)
        .boxed()
        .collect(Collectors.toList());
    when(mock.getTemperatures(anyInt())).thenReturn(temperatures);
    when(mock.getLastTemperature()).thenReturn(10);
    ctxMock.putBean(mock);

  }

  @Test
  @DirtiesContext
  public void Pageが表示される() {
    sut.startPage(GraphPage.class);
    sut.assertRenderedPage(GraphPage.class);
  }

}
