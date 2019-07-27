# Test

## Note

I have been using `Spark`, because in a bigger context of data ingestion and streaming platform
would quite fit best. It is not strictly required to use it. But it is a nice show-case too.

Anyway i provided, some similar alternative solution, with R and knitr and Zeppelin. 

Both could be connected to Spark as well 

## Using Apache Zeppelin

please run 
```bash
docker run -p 8080:8080 --rm -v $PWD/logs:/logs -v $PWD/notebook:/notebook -e ZEPPELIN_LOG_DIR='/logs' -e ZEPPELIN_NOTEBOOK_DIR='/notebook' --name zeppelin apache/zeppelin:0.8.0
```

then open the url `http://localhost:8080/#/notebook/2EJXYS9U9`

after look at the notebook.

## R

open the file `report.rmd` and run `knit` in RStudio.

## with Spark Scala

### Requisites on Windows

- `winutils` required to create a self-contained Spark instance (https://github.com/steveloughran/winutils)
- `HADOOP_HOME` and set the env variable. 

### usage

please refer to the link here: http://spark.apache.org/docs/latest/quick-start.html#self-contained-applications

#### for simplicity

use `sbt` and just run `sbt run` to execute the spark script.




## Questions

1. A well defined solution: what assumptions did you make, why did you choose this approach and how do we run it?
2. A clear report outlining the anomalies found in the data
3. The efficiency of the approach to finding the anomalies
4. Given more time, what would you do to improve your solution?


## Answers

1. I assumed the input for this ingestion test is a "data-lake", so a finite file to read at time.
   So i assumed, "I have the unziped file", now i have to start processing it.
   Assumed that a device can run 1 or more tests simultaneosly, not required to have a unique (UUID, timestamp) key.
   But for the sake of the test i removed duplicated rows.
2. This part is more on data analytics aspect, success/failure ratio, ... ??? 
3. well it is not yet clear to me the definition of anomalies in this dataset, so cannot neither answer to efficiency.
   Anyway it has used an approach of analyzing in batch (whole file)
4. Streaming from Url the file and gzip on the fly while processing, using probably library likes
   `akka`, `better-files`. 
   
   Potentially could be paired with a "fetcher service" to download in a temporary directory the file(s).
   Using a file monitor technique, event I/O based, than a listener is starting processing
   when a new file has dumped into the data directory. Providing a single process per file.
   Multi-threading processing potentially.
   Using some `application.conf` file for parametrize it. (too "early optimization" anyway)
   Using a better advanced architecture of distributed data streaming and analytic platform.
   Better structiring the code
   