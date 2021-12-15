package ua.com.epam.agar.hackathon.api.game.storage.local

class NoDataExistException: Exception("Method `exist` should be called in order to check if field exist!")