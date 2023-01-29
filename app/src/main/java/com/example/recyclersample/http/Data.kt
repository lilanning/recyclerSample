package com.example.recyclersample.http


data class Data(
    val code: Int,
    val data: Test
)

data class Info(
    val name: String,
    val picurl: String,
    val url: String
)
data class Image(
    val code: Int,
    val imgurl: String
)

data class Test(
    val kg_nick: String,
    val phonemodel: String,
    val pic: String,
    val play_num: Int,
    val playurl: String,
    val song_name: String
)