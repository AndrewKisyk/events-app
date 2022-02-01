package com.evapps.event.models

class FakePosts {
    companion object {
        val events: Array<Post> = arrayOf(
            Post(1, FakeUsers.users[0], FakeEvents.events[0], "Now"),
            Post(2, FakeUsers.users[1], FakeEvents.events[1], "2 min ago"),
            Post(3, FakeUsers.users[2], FakeEvents.events[2], "3 days ago"),
            Post(4, FakeUsers.users[3], FakeEvents.events[3],"last month"),
            Post(5, FakeUsers.users[4], FakeEvents.events[4], "2 moth ago")
        )
    }
}