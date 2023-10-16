package com.example.desh

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Splash_activity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            /*installSplashScreen().apply {

                setOnExitAnimationListener { sp ->
                    *//* when (sharedHelper.session){
                         GUEST -> openDestination(GUEST)
                         SIGNED -> openDestination(SIGNED)
                     }*//*
                    sp.remove()
                }
            }*/
        } else {
            setTheme(R.style.Theme_Splash)


        }
        setContentView(R.layout.activity_main)
    }
}