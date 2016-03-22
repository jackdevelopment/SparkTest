# SparkTest
Test spark submit

Build Example Jar File:
<pre>
[user@server SparkTest]$ gradle clean build -x test
</pre>

Run Spark-Submit command:

WordCount Example
<pre>
[user@server bin]$ ./spark-submit --master spark://host1:7077 --deploy-mode cluster 
                                  --class idv.jack.example.WordCount /home/user1/SparkTest/build/libs/SparkTest.jar 
                                  file:///home/user1/aaa.txt file:///home/user1/sparkresult/output1
</pre>

SumData Example
<pre>
[user@server bin]$ ./spark-submit --master spark://host1:7077 --deploy-mode cluster 
                                  --class idv.jack.example.SumData /home/user1/SparkTest/build/libs/SparkTest.jar                                              file:///home/user1/bbb.txt file:///home/user1/sparkresult/output3
</pre>
