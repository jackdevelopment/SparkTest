package idv.jack.example
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SumData {
  def main(args:Array[String]){

    val conf = new SparkConf().setAppName("SumData Example")
    val sc = new SparkContext(conf)
    
    val textFile = sc.textFile(args(0))

    val sum = textFile.flatMap(line => line.split("\n"))
                       .map(word => (word.split(",")(0), word.split(",")(1).toInt))
                       .reduceByKey(_+_)
    
     sum.saveAsTextFile(args(1))  
    
  }
}