package com.indicorp.loginapi.Data

//data class UserEmail(
//    val Email: String

//)
//data class UserPassword(
//    val Password  : String
//)
data class UserLogin(
    val Email: String,
    val Password: String,
    val message: String,
    val name: String,
    val success: String,
    val token: String,
//    val useremail: UserEmail,
//    val userpassword : UserPassword
)
//{
//    val status: String
//        get() {
//
//            return if (success == "true") "success" else "failure"
//        }
//}
