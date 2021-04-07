package com.pintu.neostore_kotlin.model


data class APIMsg(
    val `data`: Data,
    val message: String,
    val status: Int,
    val user_msg: String
)
