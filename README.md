# CSV to AVRO

This library lets CSV to be converted into the Avro files

## Including the library

The CSVAvro is available . You can just add the following dependency in `sbt`:

```
libraryDependencies += "com.trn.avro" % "csv-avro" % "0.0.1"
```


If you don't like sbt or Maven, you can also check out this Github repo and execute the following command from the root folder:

    sbt package

SBT will create the library jar under `target/scala-2.10`.

## Usage

To create an avro data file from csv:

...

1. Create an instance of ConfigFactory class with constructor param as :

	val conf = new ConfigFactory("C:/fidato/software/avro/avro-tools-1.7.7.jar", "C:/fidato/software/avro/import.csv")
	
	above shows the first parameter as the absolute path of avro-tools jar on your system
	second parameter is for absolute path of input csv
	
2. Then create the instance of CSVToAvro with the two params:

	new CSVToAvro(conf,true).processAvro
	
	first param is the ConfigFactory Instance
	second param is set to true if the avro classes generation is required 
	
...

## TODO

1. Remove avro-tools jar as the primary requirement as this is only required in case the user wants to generate the avro class
