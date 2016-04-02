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

1. Create an instance of CSVToAvro class with constructor param as :
	val csvAvro = new CSVToAvro("C:/fidato/software/avro/import.csv",true)
	
	above shows the first parameter as the absolute path of csv
	second parameter is to be set to true if you require the corresponding avro class to be generated.
	
...

## TODO

1. Remove hardcoding of avro-tools jar and put that into a property file
2. Also, add some more parameters to the property file or create a configuration class to hold all the values
