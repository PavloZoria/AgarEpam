package ua.com.epam.agar.engine.provider.host

import ua.com.epam.agar.hackathon.api.Host

interface HostProvider {
    operator fun invoke() : Host
}