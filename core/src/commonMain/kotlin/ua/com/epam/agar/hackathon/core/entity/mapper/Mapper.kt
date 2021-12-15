package ua.com.epam.agar.hackathon.core.entity.mapper

interface Mapper<To, From> : MapTo<To, From>, MapFrom<From, To>
