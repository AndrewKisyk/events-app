package com.evapps.event.screens.log_in

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.evapps.event.EventApp
import com.evapps.event.R
import com.evapps.event.screens.base.BaseActivity
import com.evapps.event.screens.log_in.interfaces.AppBarController
import com.evapps.event.screens.log_in.view_models.UserViewModel
import com.google.android.material.appbar.MaterialToolbar

import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : BaseActivity(), AppBarController {
    @Inject
    lateinit var viewModel: UserViewModel

    //Navigation
    lateinit var navHostFragment: NavHostFragment
    lateinit var inflater: NavInflater
    lateinit var graph: NavGraph
    lateinit var navController:NavController
    lateinit var bottomNav: BottomNavigationView
    lateinit var appBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        EventApp.appComponent.inject(activity = this)

        initNavigation()

        setUpBottomNav(navController)
                hideBottomNavigation()
                hideBar()
                setUpLoginGraph()
//        viewModel.user?.observe(this, Observer {
//            if(it != null) {
//                showBottomNavigation()
//                showBar()
//                setUpMainGraph()
//            } else {
//                hideBottomNavigation()
//                hideBar()
//                setUpLoginGraph()
//            }
//        } )



    }

    private fun initNavigation(){
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        inflater = navHostFragment.navController.navInflater
        navController = navHostFragment.navController
    }

    private fun setUpMainGraph(){
        graph = inflater.inflate(R.navigation.main_nav_graph)
        navController.setGraph(graph, intent.extras)
    }

    private fun setUpLoginGraph(){
        graph = inflater.inflate(R.navigation.login_nav_graph)
        navController.setGraph(graph, intent.extras)
    }

    private fun setUpBottomNav(navController: NavController) {
        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav.setupWithNavController(navController)
        bottomNav.setOnApplyWindowInsetsListener(null)
    }

    private fun hideBottomNavigation(){
        bottomNav.visibility = View.GONE
    }

    private fun showBottomNavigation(){
        bottomNav.visibility = View.VISIBLE
    }

    private fun showBar(){
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        topAppBar.visibility = View.VISIBLE
        appbar.visibility = View.VISIBLE
    }

    private fun hideBar(){
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        topAppBar.visibility = View.GONE
        appbar.visibility = View.GONE
    }

    override fun showTopBar() {
        topAppBar.visibility = View.GONE
    }

    override fun hideTopBar() {
        topAppBar.visibility = View.GONE
    }

}