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
import com.mnenmenth.precalcgraphproject.ui.Graph.Line

/**
  * Created by Mnenmenth Alkaborin
  * Please refer to LICENSE file if included
  * for licensing information
  * https://github.com/Mnenmenth
  */

class GraphPoint(x_ : Double, y1_ : Double, y2_ : Double, line1: Line, line2: Line) {
  val x = new StringProperty(this, "x", x_.toString)
  val y1 = new StringProperty(this, "y1", y1_.toString)
  val y2 = new StringProperty(this, "y2", y2_.toString)
  val y1Data = XYChart.Data[Number, Number](x_, y1_)
  val y2Data = XYChart.Data[Number, Number](x_, y2_)
  line1.getData.add(y1Data)
  line2.getData.add(y2Data)
}

class Table(xName: String,
            y1Name: String,
            y2Name: String) extends TableView[GraphPoint] {

  val xCol = new TableColumn[GraphPoint, String] {
    text = xName
    //editable = true
    cellValueFactory = { _.value.x }
    cellFactory = TextFieldTableCell.forTableColumn[GraphPoint]()
    /*onEditCommit = (evt: CellEditEvent[GraphPoint, String]) => {
      val point = evt.rowValue.data
      point.setXValue(evt.newValue.toDouble)
    }*/
  }

  val y1Col = new TableColumn[GraphPoint, String] {
    text = y1Name
    //editable = true
    cellValueFactory = { _.value.y1 }
    cellFactory = TextFieldTableCell.forTableColumn[GraphPoint]()
    /*onEditCommit = (evt: CellEditEvent[GraphPoint, String]) => {
      val point = evt.rowValue.data
      point.setYValue(evt.newValue.toDouble)
    }*/
  }

  val y2Col = new TableColumn[GraphPoint, String] {
    text = y2Name
    cellValueFactory = { _.value.y2 }
    cellFactory = TextFieldTableCell.forTableColumn[GraphPoint]()
  }

  columns ++= List(xCol, y1Col, y2Col)


}
