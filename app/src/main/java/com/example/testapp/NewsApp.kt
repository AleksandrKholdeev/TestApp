package com.example.testapp

import android.app.Application
import com.example.testapp.di.AppComponent

class NewsApp : Application() {

    val myComponent: AppComponent by lazy { AppComponent() }

}