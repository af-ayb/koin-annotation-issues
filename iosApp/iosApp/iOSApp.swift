import SwiftUI
import Shared

@main
struct iOSApp: App {

    init() {
        KoinInjectHelperKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}