package ua.com.epam.agar.hackathon.core.entity.mapper

interface MapFrom<From, To> {
    fun mapFrom(item: From): To
}

fun <To, From> From.mapFrom(mapper: MapFrom<From, To>): To = run { mapper.mapFrom(this) }

fun <To, From> Iterable<From>.mapListFrom(mapper: MapFrom<From, To>): List<To> = map { mapper.mapFrom(it) }
