package org.arba.project.utils

import org.jetbrains.compose.resources.StringResource
import testkmp.composeapp.generated.resources.*


enum class Type {
    Mobile,
    Dekstop,
    Web
}

class AppConstants {
    companion object {
        const val BACK_CLICK_ROUTE = "BACK_CLICK_ROUTE"
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}

enum class Theme(val title: String) {
    SYSTEM_DEFAULT("System Default"),
    LIGHT_MODE("Light Mode"),
    DARK_MODE("Dark Mode")
}