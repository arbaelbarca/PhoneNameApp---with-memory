package org.arba.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform