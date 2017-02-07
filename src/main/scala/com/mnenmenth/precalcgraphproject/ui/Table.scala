package com.mnenmenth.precalcgraphproject.ui

import scala.collection.mutable
import scalafx.beans.property.{IntegerProperty, StringProperty}
import scalafx.collections.ObservableBuffer
import scalafx.scene.chart.XYChart
import scalafx.scene.control.TableColumn.CellEditEvent
import scalafx.scene.control.{TableCell, TableColumn, TableRow, TableView}
import scalafx.Includes._
import scalafx.scene.control.cell.TextFieldTableCell
import scalafx.util.converter.DefaultStringConverter

/**
  * Created by Mnenmenth Alkaborin
  * Please refer to LICENSE file if included
  * for licensing information
  * https://github.com/Mnenmenth
  */

class GraphPoint(x_ : Double, y_ : Double) {
  val x = new StringProperty(this, "x", x_.toString)
  val y = new StringProperty(this, "y", y_.toString)
  val data = XYChart.Data[Number, Number](x_, y_)
}

class Table(xName: String, yName: String, correspondingLine: XYChart.Series[Number, Number], dataPoints: ObservableBuffer[GraphPoint]) extends TableView[GraphPoint](dataPoints) {

  dataPoints.forEach(d=> correspondingLine.getData.add(d.data))

  editable = true

  val xCol = new TableColumn[GraphPoint, String] {
    text = xName
    editable = true
    cellValueFactory = { _.value.x }
    cellFactory = TextFieldTableCell.forTableColumn[GraphPoint]()
    onEditCommit = (evt: CellEditEvent[GraphPoint, String]) => {
      val point = evt.rowValue.data
      point.setXValue(evt.newValue.toDouble)
    }
  }

  val yCol = new TableColumn[GraphPoint, String] {
    text = yName
    editable = true
    cellValueFactory = { _.value.y }
    cellFactory = TextFieldTableCell.forTableColumn[GraphPoint]()
    onEditCommit = (evt: CellEditEvent[GraphPoint, String]) => {
      val point = evt.rowValue.data
      point.setYValue(evt.newValue.toDouble)
    }
  }
  columns ++= List(xCol, yCol)


}
