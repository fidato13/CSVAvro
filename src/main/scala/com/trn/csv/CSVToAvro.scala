package com.trn.csv

import com.trn.avro.AvroParser
import java.io.File
import java.io.BufferedWriter
import java.io.FileWriter

/**
 * @author fidato
 */
class CSVToAvro(inputCSVAbsolutePath: String, genAvroClass: Boolean = false) {

  //inputCSVAbsolutePath should contain '/' and not \\ or '\'
  def getAvro = {

    //read the csv file and create the list
    val fileLines: List[String] = io.Source.fromFile(inputCSVAbsolutePath).getLines.toList

    //create avro parser object
    val avroParseObj = new AvroParser

    //header
    val header: List[String] = fetchHeader(fileLines)

    //datarows
    val csvDataRows: List[String] = dataRows(fileLines)

    //filename without csv
    val fileName: String = getFileName(inputCSVAbsolutePath)

    //input directory
    val inputDirectory: String = getDirectory(inputCSVAbsolutePath)

    //header would be used to form the avro json schema
    val avscSchema: String = avroParseObj.headerToAvsc(header)

    // remaining list would be used to create the data avro files
    avroParseObj.genAvroData(avscSchema, header, csvDataRows, fileName, inputDirectory)

    //generate avro class
    if (genAvroClass) {
      //write avro schema to the file
      val avscFileName: String = writeSchemaFile(inputDirectory, fileName, avscSchema)

      avroParseObj.genAvroClass(inputDirectory, avscFileName)
    }
  }

  def getDirectory(inputCSVAbsolutePath: String): String =
    inputCSVAbsolutePath.split("/").toList.reverse.tail.reverse.foldLeft("")((accum, elem) => accum + elem + "/")

  def getFileName(inputCSVAbsolutePath: String): String = inputCSVAbsolutePath.split("/").toList.reverse.head.split(".csv").toList.foldLeft("")((x, y) => x + y)

  def fetchHeader(fileRows: List[String]): List[String] = fileRows.head.split(",").toList

  def dataRows(fileRows: List[String]): List[String] = fileRows.tail

  def writeSchemaFile(inputDirectory: String, fileName: String, avscSchema: String): String = {
    val avscFile = inputDirectory + fileName + ".avsc"

    val file = new File(avscFile)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(avscSchema)
    bw.close()

    avscFile

  }

}