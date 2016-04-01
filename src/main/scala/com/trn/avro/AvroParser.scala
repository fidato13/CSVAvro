package com.trn.avro

import scala.util.Try
import java.io.File
import java.io.IOException
import org.apache.avro.Schema
import org.apache.avro.file.DataFileWriter
import org.apache.avro.generic.GenericData
import org.apache.avro.generic.GenericDatumWriter
import org.apache.avro.generic.GenericRecord
import org.apache.avro.io.DatumWriter
import org.apache.avro.generic.GenericDatumReader
import org.apache.avro.file.DataFileReader

/**
 * @author fidato
 */
class AvroParser {

  val initAvsc = "{\"namespace\": \"com.trn.avro\",\"type\": \"record\",\"name\": \"ConvertedAvro\",\"fields\": ["

  val endAvsc = "]}"

  val fieldNameLiteral = "{\"name\": \""

  val fieldTypeLiteral = "\",\"type\": \"string\"}"

  val fieldSeparator = ","

  def headerToAvsc(header: List[String]): String = {

    val listButLast: List[String] = header.reverse.tail.reverse

    val lastElem: String = Try { header.last.trim }.getOrElse("")

    val fieldsAllButLast: String = listButLast.foldLeft("")((accum, c) => accum + fieldNameLiteral + Try { c }.getOrElse("") + fieldTypeLiteral + fieldSeparator)

    val fieldLast: String = fieldNameLiteral + lastElem + fieldTypeLiteral

    val finalAvsc: String = initAvsc + fieldsAllButLast + fieldLast + endAvsc

    finalAvsc

  }

  def genAvroData(avroSchema: String, header: List[String], dataList: List[String]) = {

    val schema: Schema = new Schema.Parser().parse(avroSchema)

    val listAvroData: List[(String, GenericData.Record)] = dataList.map(singleRow => {
      // here we have a single row of data ie csv data of a single row
      val listElemInARow = singleRow.split(",").toList

      // if error record
      if (listElemInARow.size != header.size) (singleRow, new GenericData.Record(schema))
      else {
        //if good record
        val zippedList = header zip listElemInARow

        //create the GenericData object
        val obj = new GenericData.Record(schema)

        //now put the values in newly created object
        zippedList.foreach { case (x, y) => obj.put(x, y) }

        //return 
        ("", obj)

      }

    }) // end listAvroData

    //now write to avro file
    writeAvroToFile(schema, listAvroData)

  }

  def writeAvroToFile(schema: Schema, listAvroData: List[(String, GenericData.Record)]) = {

    val file = new File("src/main/resources/gen-avro.avro")
    val datumWriter = new GenericDatumWriter[GenericRecord](schema)
    val dataFileWriter = new DataFileWriter[GenericRecord](datumWriter)
    dataFileWriter.create(schema, file)

    listAvroData.foreach { case (x, y) if x.length == 0 => { println("reached for => " + y); dataFileWriter.append(y) } }

    dataFileWriter.close()

  }

  def readFromAvro(avroSchema: String) = {

    val schema = new Schema.Parser().parse(avroSchema)

    //create the GenericData object
    val obj = new GenericData.Record(schema)
    
    val file = new File("src/main/resources/gen-avro.avro")
    
    
    
    val datumReader = new GenericDatumReader[GenericRecord](schema)
    val dataFileReader = new DataFileReader[GenericRecord](file, datumReader)
 
    while (dataFileReader.hasNext()) 
    {
    
    println(dataFileReader.next())
    }

  }

}