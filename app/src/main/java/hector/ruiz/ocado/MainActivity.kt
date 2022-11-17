package hector.ruiz.ocado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.ocado.navigation.NavGraph
import hector.ruiz.ocado.ui.theme.OcadoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OcadoTheme {
                NavGraph()
            }
        }
    }
}