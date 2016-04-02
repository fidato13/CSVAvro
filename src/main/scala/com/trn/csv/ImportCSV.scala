package com.trn.csv

import com.trn.avro.AvroParser
import com.trn.config.ConfigFactory

/**
 * @author fidato
 */
object ImportCSV extends App {
  
  /*val fileName = "import.csv"
  
  val fileLines = io.Source.fromFile("src/main/resources/"+ fileName).getLines.toList
  val totalRecords = fileLines.size
  
  //for the header ie first element
  val header: List[String] = fileLines.head.split(",").toList
  
  // remaining list
  val dataList: List[String] = fileLines.tail
  
  //create avro parser object
  val avroParseObj = new AvroParser
  
  //header would be used to form the avro json schema
  val avscSchema = avroParseObj.headerToAvsc(header)
  
  println("The returned Schema is => "+avscSchema)
  
  // remaining list would be used to create the data avro files
  //avroParseObj.genAvroData(avscSchema, header, dataList)
  
  //read from avro
  avroParseObj.readFromAvro(avscSchema)
  */
  
 /* val fileLines: List[String] = io.Source.fromFile("C:/fidato/software/avro/import.csv").getLines.toList
  
  println("fileLines => "+ fileLines)*/
  
 val conf = new ConfigFactory("C:/fidato/software/avro/avro-tools-1.7.7.jar", "C:/fidato/software/avro/import.csv")
 
 new CSVToAvro(conf,true).processAvro
  
  
  
  
}