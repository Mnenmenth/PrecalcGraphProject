package com.mnenmenth.precalcgraphproject.ui

import net.objecthunter.exp4j.ExpressionBuilder

import scalafx.collections.ObservableBuffer
import scalafx.scene.chart.NumberAxis
import scalafx.scene.layout.BorderPane

/**
  * Created by Mnenmenth Alkaborin
  * Please refer to LICENSE file if included
  * for licensing information
  * https://github.com/Mnenmenth
  */
class WindowContainer extends BorderPane {

  val graph = new Graph(NumberAxis("Time"), NumberAxis("Population"), "Population over Time")

  /*def calcEq(eq: String, variables:): Int = {
    val e = new ExpressionBuilder("200 * 1.02 ^ t")
      .variables("t")
      .build()
      .setVariable("t", 3)
    e.evaluate().toInt
  }*/

  def genPoints(start: Double, multiplier: Double): ObservableBuffer[GraphPoint] = {
    val points = new ObservableBuffer[GraphPoint]()
    for(i <- 0 to 40) {
      val y = BigDecimal(start * math.pow(multiplier, i)).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
      points += new GraphPoint(i, y)
    }
    points
  }

  val prey = new Table("Years", "# of Prey", graph.line1, genPoints(200, 0.91))
  val predators = new Table("Years", "# of Predators", graph.line2, genPoints(2, 1.5))

  left = prey
  right = predators
  center = graph

}
