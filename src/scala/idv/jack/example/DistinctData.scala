package idv.jack.example
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object DistinctData {
  
  def main(args:Array[String]){
    val conf = new SparkConf().setAppName("Filter Example")
    val sc = new SparkContext(conf)
    
    val textFile = sc.textFile(args(0)) 
    
    val line = textFile.distinct()
    val count = line.count()
    println(s"record count:$count") 
    line.saveAsTextFile(args(1))
    
  }
  
}