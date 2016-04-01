# CSV to AVRO

This library lets CSV to be converted into the Avro files

If you want to read and write data to HBase, you don't need using the Hadoop API anymore, you can just use Spark.

## Including the library

The CSVAvro is available . You can just add the following dependency in `sbt`:

```
libraryDependencies += "com.trn.avro" % "CSVAvro_2.10" % "0.0.1"
```


If you don't like sbt or Maven, you can also check out this Github repo and execute the following command from the root folder:

    sbt package

SBT will create the library jar under `target/scala-2.10`.
