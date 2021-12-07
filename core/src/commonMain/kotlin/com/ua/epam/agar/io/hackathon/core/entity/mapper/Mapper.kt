package com.ua.epam.agar.io.hackathon.core.entity.mapper

interface Mapper<To, From> : MapTo<To, From>, MapFrom<From, To>
