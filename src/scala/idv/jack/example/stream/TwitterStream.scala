package idv.jack.example.stream
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.SparkContext
import org.apache.spark.streaming.twitter._

import twitter4j.auth._
import twitter4j.conf._
import StorageLevel._

object TwitterStream {
  def main(args:Array[String]){
      val conf = new SparkConf().setAppName("Twitter Stream Example")
      val sc = new SparkContext(conf)
      val ssc = new StreamingContext(sc, Seconds(2))
    
      val cb = new ConfigurationBuilder
      cb.setDebugEnabled(true)
        .setOAuthConsumerKey("")
        .setOAuthConsumerSecret("")
        .setOAuthAccessToken("")
        .setOAuthAccessTokenSecret("")
       val auth = new OAuthAuthorization(cb.build)
      
       val tweets = TwitterUtils.createStream(ssc, Some(auth)) 
       val englishTweets = tweets.filter(_.getLang() == "en")
       val status = englishTweets.map(status => status.getText)
       status.print
       ssc.checkpoint("hdfs://host1:9000/user/jack/checkpoint")
       ssc.start
       ssc.awaitTermination
       
  }
}