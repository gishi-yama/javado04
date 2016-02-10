package com.example.page;

import java.nio.charset.StandardCharsets;

import lombok.val;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import com.example.service.IBarService;

@MountPath("api")
public class WebPageAsAPI extends WebPage {
  private static final long serialVersionUID = 2280660175583931605L;

  private static final String JSON_TEMPLATE = "{ \"returning\" : \"%s\" }";
  private static final String JSON_OK = "OK";
  private static final String JSON_NG = "NG";

  private static String PARAM_NAME = "value";
  private static int ERROR_VALUE = -1;

  @SpringBean
  private IBarService barService;

  public WebPageAsAPI() {
    val params = getRequest().getRequestParameters();
    val pv = params.getParameterValue(PARAM_NAME).toInt(ERROR_VALUE);
    
    String returning = JSON_NG;
    if (pv > 0) {
      barService.addTemperature(pv);
      returning = JSON_OK;
    }

    RequestCycle.get().scheduleRequestHandlerAfterCurrent(
        new TextRequestHandler("application/json", StandardCharsets.UTF_8.toString(), String.format(JSON_TEMPLATE, returning)));
  }

}
