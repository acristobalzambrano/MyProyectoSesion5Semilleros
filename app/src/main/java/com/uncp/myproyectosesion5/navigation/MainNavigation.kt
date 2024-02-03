package com.uncp.myproyectosesion5.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uncp.myproyectosesion5.R
import com.uncp.myproyectosesion5.data.models.DrawerMenu
import com.uncp.myproyectosesion5.data.models.MainRoute
import com.uncp.myproyectosesion5.presentation.screens.Screen1
import com.uncp.myproyectosesion5.presentation.screens.Screen2
import com.uncp.myproyectosesion5.presentation.screens.Screen3
import com.uncp.myproyectosesion5.ui.theme.SkyBlueLight
import com.uncp.myproyectosesion5.ui.viewmodel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    val menus = arrayOf(
        DrawerMenu(
            title = "Articles",
            icon = ImageVector.vectorResource(id = R.drawable.ic_article),
            route = MainRoute.Articles.name
        ),
        DrawerMenu(
            title = "Settings",
            icon = ImageVector.vectorResource(id = R.drawable.ic_settings),
            route = MainRoute.Settings.name
        ),
        DrawerMenu(
            title = "About Us",
            icon = ImageVector.vectorResource(id = R.drawable.ic_about_us),
            route = MainRoute.About.name
        ),
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(menus) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = MainRoute.Articles.name) {
            composable(MainRoute.Articles.name) {
                Screen1(drawerState, MainActivityViewModel())
            }
            composable(MainRoute.About.name) {
                Screen2(drawerState)
            }
            composable(MainRoute.Settings.name) {
                Screen3(drawerState)
            }
        }
    }
}

@Composable
private fun DrawerContent(
    menus: Array<DrawerMenu>,
    onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SkyBlueLight)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                imageVector = Icons.Filled.AccountCircle,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        menus.forEach {
            NavigationDrawerItem(
                modifier = Modifier.background(color = SkyBlueLight),
                label = { Text(text = it.title) },
                icon = { Icon(imageVector = it.icon, contentDescription = null) },
                selected = false,
                onClick = {
                    onMenuClick(it.route)
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = SkyBlueLight,
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black
                )
            )
        }
    }
}