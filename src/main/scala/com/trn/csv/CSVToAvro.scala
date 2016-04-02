package com.trn.csv

import com.trn.avro.AvroParser
import java.io.File
import java.io.BufferedWriter
import java.io.FileWriter
import com.trn.config.ConfigFactory
import scala.reflect.internal.util.NoSourceFile
import java.nio.file.NoSuchFileException

/**
 * @author fidato
 */
class CSVToAvro(config: ConfigFactory, genAvroClass: Boolean = false) {

  def processAvro = {
    //pre processing validations here, if succeded then call the method createAvro
    
    config match {
      case x if (x.input_csv != null && x.input_csv.length > 0 && x.avro_tools != null && x.avro_tools.length > 0) => createAvro
      case _ => println("invalid request")
    }
  }
  
  //inputCSVAbsolutePath should contain '/' and not \\ or '\'
  private def createAvro = {

    //read the csv file and create the list
    val fileLines: List[String] = io.Source.fromFile(config.input_csv).getLines.toList

    //create avro parser object
    val avroParseObj = new AvroParser(config)

    //header
    val header: List[String] = fetchHeader(fileLines)

    //datarows
    val csvDataRows: List[String] = dataRows(fileLines)

    //filename without csv
    val fileName: String = getFileName(config.input_csv)

    //input directory
    val inputDirectory: String = getDirectory(config.input_csv)

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