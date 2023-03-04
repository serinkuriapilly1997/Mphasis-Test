package com.app.mymainapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.app.mymainapp.R
import com.app.mymainapp.baseresult.BaseResult
import com.app.mymainapp.databinding.ActivityMainBinding
import com.app.mymainapp.databinding.ActivitySplashBinding
import com.app.mymainapp.listeners.OnItemClickListener
import com.app.mymainapp.localdatabaseservice.entities.StudentEntity
import com.app.mymainapp.models.TestApiResponseModel
import com.app.mymainapp.networkconnectivity.NetworkConnectivityManager
import com.app.mymainapp.preferences.PreferenceHandler
import com.app.mymainapp.rxbus.RxBus
import com.app.mymainapp.rxbus.RxEvent
import com.app.mymainapp.ui.adapters.TestAdapter
import com.app.mymainapp.ui.home.HomeActivity
import com.app.mymainapp.utils.StylishToastyUtils
import com.app.mymainapp.utils.hide
import com.app.mymainapp.utils.show
import com.app.mymainapp.utils.showToast
import com.app.mymainapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var preferenceHandler: PreferenceHandler

    @Inject
    lateinit var stylishToastyUtils: StylishToastyUtils


    private lateinit var binding: ActivitySplashBinding

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var testAdapter: TestAdapter

    private lateinit var personDisposable: Disposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        navigateToHome()

    }

    private fun navigateToHome() {

        Timer().schedule(2000) {

            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()

        }
    }


}