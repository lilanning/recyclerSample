package com.example.recyclersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recyclersample.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {
    private lateinit var naviBinding: ActivityNavigationBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        naviBinding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(naviBinding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragment_container
        ) as NavHostFragment
        navController = navHostFragment.navController

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        bottomNavigationView.setupWithNavController(navController)

        naviBinding.bottomNav.setupWithNavController(navController)

        //设置标题栏标题  对应navigation中的android:label="FirstFragment"属性
        //在处于set集合里的目的地时，不会出现up返回按钮
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_weiXin,
                R.id.fragment_tongXnLu,
                R.id.fragment_FaXian,
                R.id.fragment_My
            )
        )
        //设置AppCompatActivity返回的ActionBar。getSupportActionBar用于NavController。
        // 通过调用此方法，当目标更改时，操作栏中的标题将自动更新(假设存在有效的标签)。
        setupActionBarWithNavController(navController, appBarConfiguration)

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}