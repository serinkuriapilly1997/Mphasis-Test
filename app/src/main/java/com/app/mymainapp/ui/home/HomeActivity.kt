package com.app.mymainapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.app.mymainapp.R
import com.app.mymainapp.baseresult.BaseResult
import com.app.mymainapp.databinding.ActivityHomeBinding
import com.app.mymainapp.databinding.ActivityMainBinding
import com.app.mymainapp.generated.callback.OnClickListener
import com.app.mymainapp.listeners.OnItemClickListener
import com.app.mymainapp.localdatabaseservice.entities.StudentEntity
import com.app.mymainapp.models.TestApiResponseModel
import com.app.mymainapp.networkconnectivity.NetworkConnectivityManager
import com.app.mymainapp.preferences.PreferenceHandler
import com.app.mymainapp.rxbus.RxBus
import com.app.mymainapp.rxbus.RxEvent
import com.app.mymainapp.ui.adapters.PageAdapter
import com.app.mymainapp.ui.adapters.TestAdapter
import com.app.mymainapp.utils.StylishToastyUtils
import com.app.mymainapp.utils.hide
import com.app.mymainapp.utils.show
import com.app.mymainapp.utils.showToast
import com.app.mymainapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var preferenceHandler: PreferenceHandler

    @Inject
    lateinit var stylishToastyUtils: StylishToastyUtils


    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.listener = this
        setupViewpager()
    }

    private fun setupViewpager() {

        binding.viewPager.adapter = PageAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onClick(view: View?) {

        when (view) {


        }
    }


}