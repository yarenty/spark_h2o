package com.yarenty.spark_h2o


import ai.h2o.sparkling._
import ai.h2o.sparkling.utils.SparkSessionUtils
import org.apache.spark.SparkConf

object Main extends App { //SparklingWaterApp with SparkContextSupport {

  //  import java.lang.reflect.Field
  //  import java.nio.charset.Charset
  //  System.setProperty("file.encoding", "UTF-8")
  //  val charset: Field = classOf[Charset].getDeclaredField("defaultCharset")
  //  charset.setAccessible(true)
  //  charset.set(null, null)

  
  // Configure this application
  val conf: SparkConf = H2OConf.checkSparkConf(
    new SparkConf()
      .setAppName("Sparkling Water Test")
      .setIfMissing("spark.master", sys.env.getOrElse("spark.master", "local[*]"))
      .set("spark.ext.h2o.repl.enabled", "true"))

  //  implicit val sqlContext = SparkSession.builder().getOrCreate().sqlContext
  //  sqlContext.sql("SET spark.sql.autoBroadcastJoinThreshold=-1")
  //  Log.setLogLevel("INFO",false)

  SparkSessionUtils.createSparkSession(conf)
  // Start H2O cluster only
  val hc = H2OContext.getOrCreate()

  println(hc) // scalastyle:ignore

  // Infinite wait
  this.synchronized(while (true) {
    wait()
  })
  
  // Shutdown - if web portal is not available as there will be no access channel.
  // if (!fmaConf.h2oWebPortal) H2O.exit(0)
}