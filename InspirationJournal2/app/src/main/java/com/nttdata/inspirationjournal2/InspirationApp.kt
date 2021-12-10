package com.nttdata.inspirationjournal2

import android.app.Application
import com.nttdata.inspirationjournal2.data.AuthRepository
import com.nttdata.inspirationjournal2.data.InspirationRepository
import com.nttdata.inspirationjournal2.data.local.dao.InspirationDao
import com.nttdata.inspirationjournal2.data.remote.AuthRetrofit
import com.nttdata.inspirationjournal2.data.remote.AuthService
import com.nttdata.inspirationjournal2.data.remote.InspirationRetrofit

class InspirationApp: Application() {

    private val inspirationService by lazy {
        InspirationRetrofit.createService()
    }
    private val inspirationDao by lazy {
        InspirationDao()
    }
    private val authService by lazy {
        AuthRetrofit.createService()
    }

    val inspirationRepository: InspirationRepository by lazy {
        InspirationRepository(inspirationDao, inspirationService)
    }

    val authRepository: AuthRepository by lazy {
        AuthRepository(authService)
    }
}