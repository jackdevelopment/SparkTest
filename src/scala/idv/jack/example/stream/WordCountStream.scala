package idv.jack.example.stream
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.SparkContext
import StorageLevel._

object WordCountStream {
  
  def main(args:Array[String]){
      val conf = new SparkConf().setAppName("WordCount Stream Example")
      val sc = new SparkContext(conf)
      val ssc = new StreamingContext(sc, Seconds(2))
    
      //$ nc -lk 9999
      val lines = ssc.socketTextStream("localhost", 9999, MEMORY_ONLY)
      val wordsFlatMap = lines.flatMap(_.split(" "))
      val wordsMap = wordsFlatMap.map(w => (w, 1))
      val wordCount = wordsMap.reduceByKey((a, b) => (a + b))
      wordCount.print()
      print(wordCount)
      ssc.start()
      ssc.awaitTermination()      
  }
  
}