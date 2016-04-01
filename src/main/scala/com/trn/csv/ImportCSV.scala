package com.trn.csv

import com.trn.avro.AvroParser

/**
 * @author fidato
 */
object ImportCSV extends App {
  
  val fileName = "import.csv"
  
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
  avroParseObj.genAvroData(avscSchema, header, dataList)
  
  
}