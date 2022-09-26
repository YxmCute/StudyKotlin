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
    (1..3).asFlow() // a flow of requests
        .transform { request ->
            emit("Making request $request")
            emit(performRequest(request))
        }
        .collect { response -> println(response) }
}