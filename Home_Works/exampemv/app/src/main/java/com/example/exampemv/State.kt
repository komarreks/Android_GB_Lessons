package com.example.exampemv

sealed class State {
    object Waiting : State()
    object Running : State()

}