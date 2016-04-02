package com.trn.csv

object ws1 {
  
  val d = "src/main/resources/inp.csv"            //> d  : String = src/main/resources/inp.csv
  
  d.split("/").toList.reverse.head.split(".csv").toList.foldLeft("")((x,y) => x+y)
                                                  //> res0: String = inp
  
  d.split("/").toList.reverse.tail.reverse.foldLeft("")((accum, elem) => accum  + elem + "/")
                                                  //> res1: String = src/main/resources/
  
}