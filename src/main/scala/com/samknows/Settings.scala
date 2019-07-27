package com.samknows

import com.typesafe.config.ConfigFactory

object Settings {
  val config = ConfigFactory.load()
  val configData = config.getConfig("data")

  val urlZippedData = configData.getString("urlZipped")
  val destFilename = configData.getString("destName")
}
