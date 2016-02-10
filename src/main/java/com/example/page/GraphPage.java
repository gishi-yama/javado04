package com.example.page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.wicketstuff.annotation.mount.MountPath;

import com.example.page.component.TemperatureChart;
import com.googlecode.wickedcharts.highcharts.options.series.Point;

@MountPath("GraphPage")
public class GraphPage extends BasePage {
  private static final long serialVersionUID = 4493312519127342541L;


  public GraphPage() {

    add(new TemperatureChart("chart", 20, 40) {
      private static final long serialVersionUID = -2701378916725353371L;

      @Override
      public List<Point> getHistoryPoints(int xAxisSize) {
        return IntStream.rangeClosed(1, xAxisSize)
            .boxed()
            .map(i -> new Point(i, i))
            .collect(Collectors.toList());
      }

      @Override
      public Point getLastPoint(int xAxisNum) {
        return new Point(xAxisNum, Math.random() * 10);
      }
    });

  }

}
