package com.example.compostproject.utils

data class RandomUser(
    val name: String = "아냐",
    val description: String = "오늘도 공부 중",
    val profileImageUrl: String = "https://randomuser.me/api/portraits/women/72.jpg"
)

object DummyDataProvider {
    val userList = List<RandomUser>(200) { RandomUser() }
}