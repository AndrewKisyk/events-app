package com.evapps.event.models

class FakeUsers {
    companion object{
        val users: Array<User> = arrayOf(
            User(1, "Anna Burk", "Main Artist in global Direction", "https://i.pinimg.com/originals/93/08/2d/93082d420b7042fecd4b10448bc02d26.jpg"),
            User(2, "John Smith", "Designer", "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
            User(3, "Julia Shine", "Journalist in Our Days", "https://s3-us-east-2.amazonaws.com/maryville/wp-content/uploads/2020/03/02132533/tv-news-reporter-500x333.jpg"),
            User(4, "Igor Whine", "Guitarist", "https://image.freepik.com/free-photo/guitarist-man-playing-guitar-home_144627-28083.jpg"),
            User(5, "Steve Ravel", "Energy Style athlete", "https://barbend.com/wp-content/uploads/2019/04/Hafthor-Bjornsson-Europes-Strongest-Man.png")
        )
    }
}