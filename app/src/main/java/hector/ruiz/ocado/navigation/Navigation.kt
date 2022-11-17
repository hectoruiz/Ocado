package hector.ruiz.ocado.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hector.ruiz.ocado.screen.ProductDetailScreen
import hector.ruiz.ocado.screen.ProductListScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = START_DESTINATION) {
        composable(START_DESTINATION) {
            ProductListScreen {
                navController.navigate("$FINAL_DESTINATION/$it")
            }
        }
        composable(route = "$FINAL_DESTINATION/{$PARAM}",
            arguments = listOf(navArgument(PARAM) { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(PARAM)?.let { ProductDetailScreen(it) }
        }
    }
}

private const val START_DESTINATION = "productList"
private const val FINAL_DESTINATION = "productDetail"
private const val PARAM = "productId"