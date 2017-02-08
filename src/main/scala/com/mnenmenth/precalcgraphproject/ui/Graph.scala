package com.mnenmenth.precalcgraphproject.ui

import scalafx.geometry.Side
import scalafx.scene.chart.{Axis, LineChart, NumberAxis, XYChart}

/**
  * Created by Mnenmenth Alkaborin
  * Please refer to LICENSE file if included
  * for licensing information
  * https://github.com/Mnenmenth
  */

object Graph{
  type Line = XYChart.Series[Number, Number]
}

class Graph(xAxis: NumberAxis, yAxis: NumberAxis, gTitle: String, line1Name: String, line2Name: String) extends LineChart[Number, Number](xAxis, yAxis) {

  def setMax(x: Int, y: Int): Unit = {
    xAxis.upperBound = x
    yAxis.upperBound = y
  }

  def setMin(x: Int, y: Int): Unit = {
    xAxis.lowerBound = x
    yAxis.lowerBound = y
  }

  xAxis.autoRanging = false
  yAxis.autoRanging = false
  title = gTitle

  animated = true
  legendSide = Side.Bottom
  legendVisible = true

  val line1 = new XYChart.Series[Number, Number]()
  line1.name = line1Name
  val line2 = new XYChart.Series[Number, Number]()
  line2.name = line2Name

  this.getData.add(line1)
  this.getData.add(line2)

  legendSide = Side.Right

}

