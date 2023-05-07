package com.example.wt

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wt.viewmodel.WeatherRepository
import com.example.wt.viewmodel.WeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}

val appModule = module {
    factory { WeatherRepository() }
    viewModel { WeatherViewModel(get()) }
}