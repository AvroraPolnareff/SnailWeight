package com.example.tryhard0001

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class TryHardApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {

    }
}