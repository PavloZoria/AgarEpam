package ua.com.epam.agar.engine.provider.host

import ua.com.epam.agar.hackathon.api.Host

object DefaultHostProvider : HostProvider {
    override fun invoke(): Host = Host("45.77.67.171")
}