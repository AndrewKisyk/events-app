package com.evapps.event.models

class FakeEvents {
    companion object {
        val events: Array<Event> = arrayOf(
            Event(
                1,
                "Snow Art Event",
                "https://images.fineartamerica.com/images/artworkimages/mediumlarge/1/prague-winter-charles-bridge-yuriy-shevchuk.jpg",
                495,
                FakeUsers.users[0],
                "Old Town quarter of Prague",
                "22.02/25.02",
                "The spectacular design in the southern city of Espoo was the work of amateur artist Janne Pyykkö and 11 volunteers. Using rope and snowshoes, Mr Pyykkö and his team created the artwork over two days last weekend. Measuring 160m (525ft) across, the artwork is thought to be the largest snow drawing ever made in the country."
            ),
            Event(
                2,
                "Design Workshop",
                "https://www.designworkshop.com/img/home_workshop.jpg",
                30,
                FakeUsers.users[1],
                "Kyiv, Crystal Park",
                "12:00 10.03",
                "We are dedicated to creating quality landscapes that meet today’s needs and endure for future generations. We use landscape architecture, urban design, planning and strategic services to create a resilient legacy for our clients, our communities and the well-being of our planet."
            ),
            Event(
                3,
                "Edinburgh International Book Festival",
                "https://www.creativecarbonscotland.com/wp-content/uploads/2016/01/Book-Festival-logo.jpg",
                23,
                FakeUsers.users[2],
                " 5 Charlotte Square, Edinburgh",
                "14/07",
                "The Edinburgh International Book Festival (EIBF) is a book festival that takes place in the last three weeks of August every year in Charlotte Square in the centre of Scotland’s capital city, Edinburgh. Billed as The largest festival of its kind in the world,[1] the festival hosts a concentrated flurry of cultural and political talks and debates, along with its well-established children's events programme."
            ),
            Event(
                4,
                "Music Festival",
                "https://www.anarapublishing.com/wp-content/uploads/elementor/thumbs/photo-1506157786151-b8491531f063-o67khcr8g8y3egfjh6eh010ougiroekqaq5cd8ly88.jpeg",
                243,
                 FakeUsers.users[3],
                "Hampton Court Palace, London",
                "8 – 19 June",
                "Boutique festivals don’t get much better than this. Artists perform to a beautiful backdrop in Henry VIII’s open-air Base Court for an intimate audience of 3,000 at what is sure to be one of the summer’s biggest celebrations. This year’s line-up includes Tom Jones, Lionel Richie and Bastille. It’s also the perfect festival if a picnic on the lawn pre-show is on your check-list. We’re very much in. Buy tickets at hamptoncourtpalacefestival.com"
            ),

            Event(
                5,
                "Strongman champions league competitors",
                "https://cdn.shopify.com/s/files/1/1478/5000/files/P7R09678_copy_1600x.jpg?v=1572726648",
                525,
                FakeUsers.users[4],
                "Various international locations",
                "~0.9",
                "The competitors include some of the top athletes in the sport, including Žydrūnas Savickas, Krzysztof Radzikowski, Travis Ortmayer, Nick Best, Mikhail Koklyaev, Ervin Katona, Andrus Murumets, Laurence Shahlaei, Vytautas Lalas and Terry Hollands."
            )

        )
    }
}