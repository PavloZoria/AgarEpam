package ua.com.epam.agar.hackathon.core.entity.mapper

interface MapTo<From, To> {
    fun mapTo(item: From): To
}

fun <From, To> From.mapTo(mapper: MapTo<From, To>): To = run { mapper.mapTo(this) }

fun <From, To> List<From>.mapListTo(mapper: MapTo<From, To>): List<To> = map { mapper.mapTo(it) }

fun <From, To> MapTo<From, To>.mapToSafely(item: From): To? = try {
    mapTo(item)
} catch (e: Exception) {
    println("Exception during mapToSafely: $e")
    null
}
