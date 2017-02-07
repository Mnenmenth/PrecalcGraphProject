package com.mnenmenth.precalcgraphproject.ui

import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

/**
  * Created by Mnenmenth Alkaborin
  * Please refer to LICENSE file if included
  * for licensing information
  * https://github.com/Mnenmenth
  */
class MainWindow extends PrimaryStage {

  val mainScene = new Scene()
  val container = new WindowContainer
  mainScene.root = container

  scene = mainScene

  //this.fullScreen = true

  title = "Pre-Calc Graph Project"

}
