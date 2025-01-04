package org.arba.project.ui.routes

object Graph {
    const val RootScreenGraph = "RootGraph"
}

sealed class Routes(var route: String) {
    data object Home : Routes("home")
    data object Bookmark : Routes("bookmark")
}
