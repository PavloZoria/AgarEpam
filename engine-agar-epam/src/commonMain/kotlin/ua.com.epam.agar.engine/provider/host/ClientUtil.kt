package ua.com.epam.agar.engine.provider.host

fun clientUrl(room: String): String = "http://${DefaultHostProvider().host}/client/?room=$room"