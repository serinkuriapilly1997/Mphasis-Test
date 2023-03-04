package com.app.mymainapp.ui.peopleDetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.mymainapp.R
import com.app.mymainapp.databinding.ActivityPeopleDetailBinding
import com.app.mymainapp.models.PeopleResponseItem
import com.app.mymainapp.preferences.PreferenceHandler
import com.app.mymainapp.utils.StylishToastyUtils
import com.app.mymainapp.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PeopleDetailActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var preferenceHandler: PreferenceHandler

    @Inject
    lateinit var stylishToastyUtils: StylishToastyUtils
    private var item: PeopleResponseItem? = null

    private lateinit var binding: ActivityPeopleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_people_detail)
        binding.listener = this
        getIntentData()

    }


    private fun getIntentData() {


        item = intent.getParcelableExtra<PeopleResponseItem>("item")
        val firstName = item?.firstName ?: ""
        val lastName = item?.lastName ?: ""
        val image = item?.avatar ?: ""
        val email = item?.email ?: ""
        val designation = item?.jobtitle ?: ""
        val color = item?.favouriteColor ?: ""

        binding.textViewName.text = "$firstName $lastName"
        binding.emailTextView.text = email
        binding.textViewDesignation.text = designation
        binding.emailTextView.text = email
        binding.colorTextView.text = "Favourite color: $color"
        loadImage(binding.imageViewAvatar, image)
    }

    override fun onClick(view: View?) {

        when (view) {

            binding.imageViewBack -> onBackPressed()
        }
    }
}