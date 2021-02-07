package com.chenjj.base.function;

public class Wine {
    String name(){
        return "Wine";
    }
}
 class SparklingWine extends Wine{
     @Override
     String name() {
         return "Sparkling Wine";
     }
 }

 class Champagne extends SparklingWine{
     @Override
     String name() {
         return "Champagne";
     }
 }


