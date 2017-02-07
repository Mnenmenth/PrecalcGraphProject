package com.mnenmenth.precalcgraphproject.ui

import scalafx.geometry.Side
import scalafx.scene.chart.{Axis, LineChart, NumberAxis, XYChart}

/**
  * Created by Mnenmenth Alkaborin
  * Please refer to LICENSE file if included
  * for licensing information
  * https://github.com/Mnenmenth
  */
class Graph(xAxis: NumberAxis, yAxis: NumberAxis, gTitle: String) extends LineChart[Number, Number](xAxis, yAxis) {

  xAxis.upperBound = 20
  xAxis.autoRanging = false
  yAxis.upperBound = 400
  yAxis.autoRanging = false
  title = gTitle

  animated = true
  legendSide = Side.Bottom
  legendVisible = true

  val line1 = new XYChart.Series[Number, Number]()
  val line2 = new XYChart.Series[Number, Number]()

  this.getData.add(line1)
  this.getData.add(line2)

}

