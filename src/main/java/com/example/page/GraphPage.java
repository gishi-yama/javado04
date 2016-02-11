package com.example.page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.val;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import com.example.page.component.TemperatureChart;
import com.example.service.ITableStorageService;
import com.googlecode.wickedcharts.highcharts.options.series.Point;

@MountPath("GraphPage")
public class GraphPage extends BasePage {
  private static final long serialVersionUID = 4493312519127342541L;

  private static final int X_AXIS_SIZE = 20;

  @SpringBean
  private ITableStorageService tableStorageService;

  public GraphPage() {

    add(new TemperatureChart("chart", X_AXIS_SIZE, 40) {
      private static final long serialVersionUID = -2701378916725353371L;

      @Override
      public List<Point> getHistoryPoints(int xAxisSize) {
        List<Point> returning = Collections.emptyList();
        try {
          val temperatures = tableStorageService.getTemperatures(xAxisSize);
          returning = IntStream.range(0, xAxisSize)
              .boxed()
              .map(i -> new Point(i + 1, temperatures.get(i)))
              .collect(Collectors.toList());
        } catch (Exception e) {
          e.printStackTrace();
        }
        return returning;
      }

      @Override
      public Point getLastPoint(int xAxisNum) {
        Point point = new Point(xAxisNum, -10);
        try {
          int y = tableStorageService.getLastTemperature();
          point.setY(y);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return point;
      }
    });

  }

}
