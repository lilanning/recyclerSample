package com.example.recyclersample.bean

data class Event(var type: Int, var message: String) {

    override fun toString(): String {
        return "type=$type--message= $message"
    }
}

