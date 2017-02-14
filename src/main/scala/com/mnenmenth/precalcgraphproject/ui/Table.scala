package com.mnenmenth.precalcgraphproject.ui

import java.text.DecimalFormat

import scala.collection.mutable
import scalafx.beans.property.{IntegerProperty, ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer
import scalafx.scene.chart.XYChart
import scalafx.scene.control.TableColumn.CellEditEvent
import scalafx.scene.control.{TableCell, TableColumn, TableRow, TableView}
import scalafx.Includes._
import scalafx.scene.control.cell.TextFieldTableCell
import scalafx.util.converter.DefaultStringConverter
import com.mnenmenth.precalcgraphproject.ui.Graph.Line

/**
  * Created by Mnenmenth Alkaborin
  * Please refer to LICENSE file if included
  * for licensing information
  * https://github.com/Mnenmenth
  */

class GraphPoint(x_ : Double, y1_ : Double, y2_ : Double, line1: Line, line2: Line) {
  val x = new ObjectProperty[Double](this, "x", x_)
  val y1 = new ObjectProperty[Double](this, "y1", y1_)
  val y2 = new ObjectProperty[Double](this, "y2", y2_)
  val y1Data = XYChart.Data[Number, Number](x_, y1_)
  val y2Data = XYChart.Data[Number, Number](x_, y2_)
  line1.getData.add(y1Data)
  line2.getData.add(y2Data)
}

class Table(xName: String,
            y1Name: String,
            y2Name: String) extends TableView[GraphPoint] {

  val xCol = new TableColumn[GraphPoint, Double] {
    text = xName
    cellValueFactory = { _.value.x }
  }

  val y1Col = new TableColumn[GraphPoint, Double] {
    text = y1Name
    cellValueFactory = { _.value.y1 }
  }

  val y2Col = new TableColumn[GraphPoint, Double] {
    text = y2Name
    cellValueFactory = { _.value.y2 }
  }

  columns ++= List(xCol, y1Col, y2Col)


}
