package com.example.kotlinbasics.kotlin_basics

fun main() {
    /*  Datatype in Kotlin  */
    val isChecked = true
    val givenCharacter = 'a'
    val givenByte: Byte = 10
    val givenString = "abcd"
    val givenInt = 100
    var givenLong = 1000L
    val givenFloat = 2.71f
    val givenDouble = 3.14
    println("\n" + isChecked + " " + givenCharacter + " " + givenByte + " " + givenString + " " + givenInt + " " +
            givenLong + " " + givenFloat + " " + givenDouble + "\n")


    /*  Type conversion  */
    val firstValue = 10
    val secondValue: Long = firstValue.toLong()
    println("$firstValue $secondValue\n")

    /*  Operator  */
    val trueValue = true
    givenLong = givenInt.toLong()              //Assignment operator (=,+=,-=,*=,/=)
    print("""$givenLong """)
    print("""${++givenLong} """)                   //Unary operator (a++,++a,+a,-a,~,!)
    print("""${givenLong + givenByte} """)         //Arithmetic operator (+,-,&,/,%)
    print("""${givenLong < givenByte} """)         //Comparison operator (<,>,<=,>=,==,!=)
    print("${isChecked && trueValue}")             //Logical operator (&&, \\)
    val getMaxValue = if (givenLong > givenInt) {
        givenLong.toInt()
    } else {
        givenLong.toInt()
    }
    println(" $getMaxValue\n")                   //replacement of ternary in kotlin

    /*  if expression  */
    if (givenInt > givenByte)
        println("$givenInt is greater than ${givenByte}\n")

    /*  if-else expression  */
    if (givenInt > givenByte)
        println("$givenInt is greater than ${givenByte}\n")
    else
        println("$givenByte is greater than ${givenInt}\n")

    /*  if-else if-else ladder   */             //will give warning to use when but it is an example of if-else if-else
    val currentInt = "150"
    if (currentInt == "50") {
        println("$currentInt is fifty\n")
    } else if (currentInt == "100") {
        println("$currentInt is hundred\n")
    } else {
        println("$currentInt is not 50 or 100\n")
    }

    /*  When statement with range */
    when (5) {
        in 1..2 -> println("In first group\n")
        in 3..4 -> println("In second group\n")
        in 5..6 -> println("In third group\n")
        in 7..8 -> println("In fourth group\n")
    }

    /*  for loop in kotlin  */
    for (i in 1..5) print("$i ")
    //for (i in 5..1) print("$i ")             // prints nothing
    println()
    for (i in 5 downTo 1) print("$i ")
    println()
    for (i in 1..5 step 2) print("$i ")
    println("\n")

    /*  while, do-while loop  */
    var sumOfNNumbers = 0
    var lastTerm = 100
    while (lastTerm != 0) {
        sumOfNNumbers += lastTerm
        --lastTerm
    }
    println("sum = $sumOfNNumbers")

    do {
        sumOfNNumbers /= 2
    } while(sumOfNNumbers > 10)
    println("sum = $sumOfNNumbers\n")

    /*  for loop with break, labelled break, continue  */
    outerMostLoop@ for (firstIndex in 0..4) {
        if (firstIndex == 1)
            continue
        for (secondIndex in 0..1) {
            if (firstIndex == 2)
                break
            if (firstIndex == 4)
                break@outerMostLoop
            println("$firstIndex $secondIndex")
        }
    }
    println()

    /*  function calling(default argument and named argument)  */
    displayBorder()                             //default arguments
    displayBorder('#')                //passing only first argument and taking second as default
    displayBorder('$',10)      //passing both arguments manually
    //displayBorder(5)                        //throws error
    displayBorder(length = 5)                 //named argument to specify which argument we are passing
    println()

    /*  lambda function  */
    val sumOfTwoNumbers: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    //val sumOfTwoNumbers = { x: Int, y: Int -> x + y }     also works
    println(sumOfTwoNumbers(10,20))

    /*  higher order function  */
    val sumResult = calculateSum(10, 20, ::sumOfTwoValues)
    val mulResult = calculateSum(10, 20) { a, b -> a * b }
    println("sumResult $sumResult, mulResult $mulResult\n")

    /*  null safety  */
    val inputString = "abc"
    println(inputString)
    //inputString = null                           //compile error
    val inputSecondString: String?                      //can be set to null
    inputSecondString = null
    println("$inputSecondString\n")

    /*  Data class  */
    val studentOne = StudentClass("Vraj")
    val studentTwo = StudentClass("Vraj")
    studentOne.ageOfStudent = 10
    studentTwo.ageOfStudent = 20
    println("First student == second student: ${studentOne == studentTwo}")
    println("First student age ${studentOne.ageOfStudent}: ${studentOne.nameOfStudent}")
    println("Second student age ${studentTwo.ageOfStudent}: ${studentTwo.nameOfStudent}\n")

    /*  inheritance and interface  */
    val objectOfCircle = Circle()
    objectOfCircle.draw()

    val objectOfPolygon = Polygon()
    objectOfPolygon.vertexCount = 10
    println(objectOfPolygon.vertexCount)

    val objectOfRectangle = Rectangle()
    println(objectOfRectangle.vertexCount)
    objectOfRectangle.vertexCount = 6
    println(objectOfRectangle.vertexCount)
    println()

    /*  usage of let, run, with, apply, also  */
    val currentString = "one"
    val currentsecondString: String? = null

    //in let, 'it' is used as default pointer
    //let returns lambda expression as a return value
    var updatedString = currentString.let { firstString ->
        if (firstString.length >= 5) firstString else "!$firstString!"
    }.uppercase()
    println(updatedString)      //output !ONE!
    updatedString = currentsecondString?.let {
        if (it.length >= 5) it else "!$it!"
    }?.uppercase().toString()
    println("$updatedString\n")     //output null

    //in run, 'this' is the default pointer and can be neglected also
    //run returns lambda expression as a return value
    val updatedSecondString = currentString.run {
        if (length >= 5) this else "!$this!"
    }.uppercase()
    println("$updatedSecondString\n")        //output !ONE!

    //in with, 'this' is the default pointer and can be neglected also
    //with returns lambda expression as a return value
    val updatedThirdString = with(updatedString) {
        if (length >= 5) this else "!$this!"
    }.uppercase()
    println("$updatedThirdString\n")        //output !NULL!

    //in apply, 'this' is the default pointer and can be neglected also
    //apply will return a context object as a return value
    val updatedFourthString = studentOne.apply {
        ageOfStudent = 21
    }
    println(updatedFourthString.printDetails())        //prints the updated object

    //in also, 'it' is the default pointer
    //also will return a context object as a return value
    val updatedFifthString = studentTwo.also {
        it.ageOfStudent = 24
    }
    println(updatedFifthString.printDetails())        //prints the updated object
    println()

    /*  Use of filter and map  */
    val values = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val evenNumbers = values.filter { it % 2 == 0 && it < 10 }
    val doubleValues = evenNumbers.map { it * 2 }
    /*  or we may write
        val evenNumbers = values.filter { it % 2 == 0 && it < 10 }.map { it * 2 }
     */
    evenNumbers.forEach { println(it) }
    println()
    doubleValues.forEach { println(it) }
    println()

    /*  use of sealed class  */
    val firstSeason = Seasons.Winter()
    val secondSeason = Seasons.Summer()
    val thirdSeason = Monsoon()
    displaySeason(firstSeason)
    displaySeason(secondSeason)
    displaySeason(thirdSeason)
    println()

    /*  e.g. for lateinit and const  */
    println(greetingMessage)
    println(::laterInitialized.isInitialized)
    greetingMessage.also { laterInitialized = it }
    println(laterInitialized)
    println(::laterInitialized.isInitialized)
}

//const's are compile time constants. Meaning that their value has to be assigned during compile time, unlike val's, where it can be done at runtime.
//const's can never be assigned to a function or any class constructor, but only to a String or primitive.
const val greetingMessage = "Hello world"

//The lateinit keyword allows you to avoid initializing a property when an object is constructed.
lateinit var laterInitialized: String

//Sealed class defines a set of subclasses within it. A sealed class is implicitly abstract and hence it cannot be instantiated.
sealed class Seasons(val x: String) {
    class Winter : Seasons("Winter")
    class Summer : Seasons("Summer")
}
class Monsoon: Seasons("Monsoon")

fun displaySeason(seasons: Seasons) {
    when(seasons) {
        is Seasons.Winter -> println("${seasons.x} is very cold")
        is Seasons.Summer -> println("${seasons.x} is very hot")
        is Monsoon -> println("${seasons.x} is the best")
    }
}

//In kotlin all the functions and classes are by default final which cannot be overridden
fun displayBorder(character: Char = '-', length: Int = 15) {
    for (i in 1..length) {
        print(character)
    }
    println()
}

fun sumOfTwoValues(x: Int, y: Int) = x + y

fun calculateSum(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

data class StudentClass(val nameOfStudent: String) {
    var ageOfStudent: Int = 0

    fun printDetails(): String {
        return "Name: $nameOfStudent, Age: $ageOfStudent"
    }
}

open class Shape {
    open fun draw() {}
}

class Circle : Shape() {
    override fun draw() {}
}

interface ShapeOfDesign {
    val vertexCount: Int
}

class Polygon : ShapeOfDesign {
    override var vertexCount: Int = 0
}

class Rectangle(override var vertexCount: Int = 4) : ShapeOfDesign