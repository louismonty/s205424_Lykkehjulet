package dtu.opgave.s205424lykkehjulet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dtu.opgave.s205424lykkehjulet.databinding.ActivityFragmentContainerBinding
import dtu.opgave.s205424lykkehjulet.databinding.ActivityMainBinding

class FramgentContainerActivity: AppCompatActivity() {
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFragmentContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the navigation controller for the navigation host
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Set up the bottom navigation for use with a navigation controller
        binding.bottomNavView.setupWithNavController(navController)

        // Setup the Action bar so the title changes to the destination
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_favourite, R.id.navigation_recent
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

    }
}