package org.sopt.sopthub.data.remote.model.response

data class ResSignInData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data: Data
){
    data class Data (
        val id : Int,
        val name : String,
        val email : String
    )
}
