package net.srirangan.opensource

import com.twitter.querulous.evaluator.QueryEvaluator

object App {

  val sourceDb = QueryEvaluator("localhost/setdb", "root", "")
  val destinationDb = QueryEvaluator("localhost/setdb", "root", "")

  def main(args: Array[String]) = {

    val boys = sourceDb.select("select id, name from boys") {
      row => (row.getInt(1), row.getString(2))
    }

    boys.foreach(boy => insertGirls(boy))
  }

  def insertGirls(boy: Tuple2[Int, String]): Unit = {
    destinationDb.execute("insert into girls (id, name) values (?, ?)", boy._1, boy._2)
  }

}
