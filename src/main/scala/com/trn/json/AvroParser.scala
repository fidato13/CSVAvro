package com.trn.json

import scala.util.Try

/**
 * @author fidato
 */
class AvroParser {
  
  
  def headerToAvsc(header: List[String]): String = {
    
    val initAvsc = "{\\\"namespace\\\": \\\"com.trn.avro\\\",\\\"type\\\": \\\"record\\\",\\\"name\\\": \\\"ConvertedAvro\\\",\\\"fields\\\": ["
    
    val endAvsc = "]}"
    
    val fieldNameLiteral = "{\\\"name\\\": \\\""
    
    val fieldTypeLiteral = "\\\",\\\"type\\\": \\\"string\\\"}"
    
    val fieldSeparator = ","
    
    val listButLast: List[String] = header.reverse.tail.reverse
    
    val lastElem: String = Try{header.last.trim}.getOrElse("")
    
    
   val fieldsAllButLast: String =  listButLast.foldLeft("")((accum, c) => accum + fieldNameLiteral  + Try{c}.getOrElse("") + fieldTypeLiteral + fieldSeparator)
   
   val fieldLast: String = fieldNameLiteral  + lastElem + fieldTypeLiteral
   
   val finalAvsc: String = initAvsc + fieldsAllButLast + fieldLast + endAvsc
   
   finalAvsc
    
  }
}