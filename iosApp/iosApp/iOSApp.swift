import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        // Initialize Koin
        AppModulesKt.initializeKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}