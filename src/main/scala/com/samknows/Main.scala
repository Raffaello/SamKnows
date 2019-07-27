package com.samknows

import org.apache.spark.sql.SparkSession

object Main {

  def main(args: Array[String]) {
    // downloadData
    // unzipData

    val spark = SparkSession.builder
      .appName("ingestion-test")
      .config("spark.master", "local")
      .getOrCreate()
    val csvData = spark.read
      .format("csv")
      .option("header", true)
      .option("inferSchema", true)
      .load(Settings.destFilename)
      .cache()

    val csvUnique = csvData.distinct().cache()

    val discardedRows = csvData.count() - csvUnique.count()

    csvUnique.createOrReplaceTempView("ingestion_unique_view")

    println("Total rows: " + csvData.count())
    println("Unique rows: " + csvUnique.count())
    println("Discarded rows: " + discardedRows)
    val successes = csvUnique
      .select("successes")
      .rdd
      .map(_(0).asInstanceOf[Int])
      .reduce(_ + _)
    val failures =
      csvUnique.select("failures").rdd.map(_(0).asInstanceOf[Int]).reduce(_ + _)

    println("Successes: " + successes)
    println("Failures: " + failures)
    PieChart.plot(successes, failures)

    spark.close()
  }
}
