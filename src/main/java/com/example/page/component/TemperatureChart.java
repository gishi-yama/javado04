package com.example.page.component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.val;

import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.AxisType;
import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.ExportingOptions;
import com.googlecode.wickedcharts.highcharts.options.Legend;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.PlotLine;
import com.googlecode.wickedcharts.highcharts.options.SeriesType;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.Tooltip;
import com.googlecode.wickedcharts.highcharts.options.livedata.LiveDataSeries;
import com.googlecode.wickedcharts.highcharts.options.livedata.LiveDataUpdateEvent;
import com.googlecode.wickedcharts.highcharts.options.series.Point;
import com.googlecode.wickedcharts.wicket7.highcharts.Chart;

public abstract class TemperatureChart extends Chart {
  private static final long serialVersionUID = 1L;
  
  private int xAxisSize;
  private int xAxisNum;
  private int yAxisNum;

  public TemperatureChart(String id, int xAxisSize, int yAxisSize) {
    super(id, null);
    this.xAxisSize = xAxisSize;
    this.xAxisNum = xAxisSize;
    this.yAxisNum = yAxisSize;
    setOptions(initOptions());
  }

  public Options initOptions() {
    Options options = new Options()
        .setChartOptions(new ChartOptions().setType(SeriesType.SPLINE))
        .setTitle(new Title("過去" + xAxisNum + "回の送信データ"));

    val xCategories = IntStream.rangeClosed(0, xAxisSize)
        .mapToObj(Integer::toString)
        .collect(Collectors.toList());

    options.setxAxis(new Axis()
        .setTitle(new Title("取得回数"))
        .setCategories(xCategories)
        .setType(AxisType.LINEAR));

    val plotLines = Collections.singletonList(new PlotLine()
        .setValue(0f)
        .setWidth(1));

    options.setyAxis(new Axis()
        .setTitle(new Title("明るさ (lux)"))
//        .setMin(-20)
//        .setMax(yAxisNum)
        .setPlotLines(plotLines));

    options.setTooltip(new Tooltip().setValueSuffix("度"));

    options.setLegend(new Legend(false));

    options.setExporting(new ExportingOptions()
        .setEnabled(false));

    val lds = new LiveDataSeries(options, 5000) {
      private static final long serialVersionUID = 332181707165805588L;

      @Override
      public Point update(LiveDataUpdateEvent event) {
        xAxisNum = xAxisNum + 1;
        return getLastPoint(xAxisNum);
      }
    };

    lds.setData(getHistoryPoints(xAxisSize));
    options.addSeries(lds);
    return options;
  }

  public abstract List<Point> getHistoryPoints(int xAxisSize);

  public abstract Point getLastPoint(int xAxisNum);
}
