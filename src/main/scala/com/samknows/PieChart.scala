package com.samknows

import org.jfree.data.general.DefaultPieDataset
import scalax.chart.module.ChartFactories

object PieChart extends scalax.chart.module.Charting {
  def plot(successes: Int, failures: Int): Unit = {
    val ds = new DefaultPieDataset()
    ds.setValue("Successes", successes)
    ds.setValue("Failures", failures)
    val pie = ChartFactories.PieChart(ds)
    pie.show("Success Ratio")
  }
}
