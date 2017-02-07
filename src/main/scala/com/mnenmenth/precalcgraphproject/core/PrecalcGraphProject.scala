package com.mnenmenth.precalcgraphproject.core

import com.mnenmenth.precalcgraphproject.ui.MainWindow

import scalafx.application.JFXApp

/**
  * Created by Mnenmenth Alkaborin
  * Please refer to LICENSE file if included
  * for licensing information
  * https://github.com/Mnenmenth
  */
object PrecalcGraphProject extends JFXApp {

  val mainWindow = new MainWindow
  stage = mainWindow

}
