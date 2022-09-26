package com.yxmcute

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello world!")
    }
}

suspend fun performRequest(request: Int): String {
    delay(1000) // imitate long-running asynchronous work
    return "response $request"
}

fun main() = runBlocking<Unit> {

    // mainTake()
   // mainTransform()
    val sum= reduce()
    println(sum)
}

suspend fun mainTransform() {
    (1..3).asFlow() // a flow of requests
        .transform { request ->
            emit("Making request $request")
            emit(performRequest(request))
        }
        .collect { response -> println(response) }
}

//take限长操作符
fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("This line will not execute")
        emit(3)
    } finally {
        println("Finally in numbers")
    }
}

fun mainTake() = runBlocking<Unit> {
    numbers().take(2).collect { value ->
        println(value)
    }
}
 suspend  fun reduce()=(1..5).asFlow()
    .map { it * it } // 数字 1 至 5 的平方
    .reduce { a, b -> a + b } // 求和（末端操作符）


