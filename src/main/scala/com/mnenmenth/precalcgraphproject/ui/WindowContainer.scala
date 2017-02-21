package com.mnenmenth.precalcgraphproject.ui

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

  val graph = new Graph(
    NumberAxis("Time (Months)"),
    NumberAxis("# In Circulation x 100,000"),
    "Fifty Shades of Gray Books vs. Marriage Licenses in Circulation",
    "Books",
    "Licenses")

  val y1Data = EqData(1, 3.5)
  val y2Data = EqData(4000, 1.35)
  var timePeriod = 12.0

  /*def calcEq(eq: String, variables:): Int = {
    val e = new ExpressionBuilder("200 * 1.02 ^ t")
      .variables("t")
      .build()
      .setVariable("t", 3)
    e.evaluate().toInt
  }*/

  val table = new Table("Month", "Books", "Licences")

    case class EqData(start: Double, multiplier: Double)

  val monthSetBox = new HBox {
    spacing = 10
    alignment = Pos.Center
  }
  val monthLabel = new Label("Months: ")
  val monthInput = new TextField {
    text = "12.0"
    prefColumnCount = 4
    text.onChange({
      if (text.value.nonEmpty)
        timePeriod = text.value.toDouble
        genPoints()
    })
  }

  val rangeLabel = new Label("Graph Range (XMax,YMax): ")
  val rangeInput = new TextField {
    prefColumnCount = 10
    text.onChange({
      if (text.value.nonEmpty) {
        try {
          val vals = text.value.split(',')
          if (vals(0).nonEmpty && vals(1).nonEmpty) {
            graph.setMax(vals(0).toDouble.toInt, vals(1).toDouble.toInt)
          }
        } catch {
          case _: ArrayIndexOutOfBoundsException => println("Yeahhhhhh naaahh")
        }
      }
    })
  }
  val rangeOverride = new CheckBox {
    text = "Manual Range"
    selected = false
  }

  monthSetBox.children.addAll(monthLabel, monthInput, rangeLabel, rangeInput, rangeOverride)

  bottom = table
  center = graph
  top = monthSetBox

  def genPoints(): Unit = {
    val points = new ObservableBuffer[GraphPoint]()
    graph.setMin(1, 0)
    for(i: Double <- 1.0 to timePeriod by 0.1) {
      val y1 = BigDecimal(y1Data.start * math.pow(y1Data.multiplier, i)).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
      val y2 = BigDecimal(y2Data.start * math.pow(y2Data.multiplier, -i)).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
      points += new GraphPoint(BigDecimal(i).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble, y1, y2, graph.line1, graph.line2)
    }
    if(!rangeOverride.selected.value) {
      val max = (timePeriod, timePeriod*100*2)
      graph.setMax(max._1.ceil.toInt, max._2.ceil.toInt)
      rangeInput.text = s"${max._1},${max._2}"
    }
    table.items = points
  }

  genPoints()

}
