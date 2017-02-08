package com.mnenmenth.precalcgraphproject.ui

import net.objecthunter.exp4j.ExpressionBuilder

import scalafx.collections.ObservableBuffer
import scalafx.geometry.Pos
import scalafx.scene.chart.NumberAxis
import scalafx.scene.control.{CheckBox, Label, TextArea, TextField}
import scalafx.scene.layout.{BorderPane, HBox}

/**
  * Created by Mnenmenth Alkaborin
  * Please refer to LICENSE file if included
  * for licensing information
  * https://github.com/Mnenmenth
  */
class WindowContainer extends BorderPane {

  val graph = new Graph(NumberAxis("Time (Years)"), NumberAxis("# Sold x 100000"), "Fifty Shades of Gray Books vs. Whip Sales Per Year", "Books", "Whips")

  /*def calcEq(eq: String, variables:): Int = {
    val e = new ExpressionBuilder("200 * 1.02 ^ t")
      .variables("t")
      .build()
      .setVariable("t", 3)
    e.evaluate().toInt
  }*/

  val table = new Table("Year", "Book Sales", "Whip Sales")

  case class EqData(start: Double, multiplier: Double, initValue: Double = 0)

  val yearSetBox = new HBox {
    spacing = 10
    alignment = Pos.Center
  }
  val yearLabel = new Label("Years: ")
  val yearInput = new TextField {
    text = "12"
    prefColumnCount = 4
    text.onChange({
      if (text.value.nonEmpty)
        genPoints(EqData(1, 2), EqData(1, 1.9, 200), text.value.toInt)
    })
  }

  val rangeLabel = new Label("Graph Range (XMax,YMax): ")
  val rangeInput = new TextField {
    prefColumnCount = 10
    text.onChange({
      if (text.value.nonEmpty) {
        val vals = text.value.split(',')
        if (vals(0).nonEmpty && vals(1).nonEmpty){
          graph.setMax(vals(0).toInt, vals(1).toInt)
        }
      }
    })
  }
  val rangeOverride = new CheckBox {
    text = "Manual Range"
    selected = false
  }

  yearSetBox.children.addAll(yearLabel, yearInput, rangeLabel, rangeInput, rangeOverride)

  left = table
  center = graph
  top = yearSetBox

  def genPoints(y1Data: EqData, y2Data: EqData, timePeriod: Int): Unit = {
    val points = new ObservableBuffer[GraphPoint]()
    graph.setMin(1, 0)
    for(i <- 1 to timePeriod) {
      val y1 = BigDecimal(y1Data.start * math.pow(y1Data.multiplier, i)).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
      val y2 = BigDecimal(y2Data.start * math.pow(y2Data.multiplier, i)).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
      points += new GraphPoint(i, y1+y1Data.initValue, y2+y2Data.initValue, graph.line1, graph.line2)
    }
    if(!rangeOverride.selected.value) {
      val max = (timePeriod + 2, timePeriod*100)
      graph.setMax(max._1, max._2)
      rangeInput.text = s"${max._1},${max._2}"
    }
    table.items = points
  }

  genPoints(EqData(1, 2), EqData(1, 1.9, 200), 12)

}
