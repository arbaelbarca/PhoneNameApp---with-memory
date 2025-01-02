package org.arba.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import org.arba.project.di.initializeKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        initializeKoin()
        setContent {
            App()
//            MainApp(contactViewModel)
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//
//}

@Composable
fun MainApp(contactViewModel: ContactViewModel) {
    MainContactPage(contactViewModel)
}