package com.ua.epam.agar.io.hackathon.core.entity.mapper

interface MapFrom<From, To> {
    fun mapFrom(item: From): To
}

fun <To, From> From.mapFrom(mapper: MapFrom<From, To>): To = run { mapper.mapFrom(this) }

fun <To, From> List<From>.mapListFrom(mapper: MapFrom<From, To>): List<To> = map { mapper.mapFrom(it) }
