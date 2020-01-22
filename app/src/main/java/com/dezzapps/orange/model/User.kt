package com.dezzapps.orange.model

data class User(val name: String, val likeOranges: Boolean){
    override fun toString(): String = "$name - $likeOranges"

}