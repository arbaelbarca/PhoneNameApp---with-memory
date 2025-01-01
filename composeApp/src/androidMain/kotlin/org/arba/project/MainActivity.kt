package org.arba.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        setContent {
            App(contactViewModel)
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