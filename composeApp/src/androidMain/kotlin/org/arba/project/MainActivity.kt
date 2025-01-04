package org.arba.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.arba.project.di.initializeKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        initializeKoin()
        setContent {
            AppAndroidPreview()
            //            MainApp(contactViewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppAndroidPreview() {
    App()
}

//@Preview(showBackground = true)
//@Composable
//fun MainApp() {
//    MainContactPage()
//}