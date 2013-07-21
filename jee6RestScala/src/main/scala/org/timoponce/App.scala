package org.timoponce

/**
 * Created with IntelliJ IDEA.
 * User: timoteo
 * Date: 12/23/12
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
object App{

  def main(args: Array[String]){
    wrap( method1( "test" ) )
    println( "app..." )
  }

  def method1(input:Any) = println( input.toString + " -- ")

  def wrap[R](block:  => R ): R = {
    println( "begin" )
    val result = block
    println( "end" )
    return result
  }
}
