package com.app.mymainapp.ui.home

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.app.mymainapp.R
import com.app.mymainapp.baseresult.BaseResult
import com.app.mymainapp.databinding.FragmentFirstBinding
import com.app.mymainapp.databinding.FragmentPeopleBinding
import com.app.mymainapp.databinding.FragmentRoomsBinding
import com.app.mymainapp.listeners.OnItemClickListener
import com.app.mymainapp.models.Name
import com.app.mymainapp.models.TestApiNestedModel
import com.app.mymainapp.ui.adapters.PeopleListAdapter
import com.app.mymainapp.ui.adapters.RoomsAdapter
import com.app.mymainapp.ui.adapters.nestedadapter.TestNestedAdapter
import com.app.mymainapp.utils.StylishToastyUtils
import com.app.mymainapp.utils.hide
import com.app.mymainapp.utils.show
import com.app.mymainapp.utils.showToast
import com.app.mymainapp.viewmodels.FragmentViewModel
import com.app.mymainapp.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class RoomsFragment : Fragment(R.layout.fragment_rooms), View.OnClickListener, OnItemClickListener {


    private val binding: FragmentRoomsBinding by viewBinding()
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var roomsAdapter: RoomsAdapter

    @Inject
    lateinit var stylishToastyUtils: StylishToastyUtils


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpList()
        fetchList()
        observeList()
    }

    private fun observeList() {

        homeViewModel.rooms.observe(viewLifecycleOwner, Observer {

            when (it.status) {
                BaseResult.Status.SUCCESS -> {

//                    stylishToastyUtils.showSuccessMessage("success")

                    binding.appLoader.hide()
                    binding.roomsRecyclerView.show()
                    it.data.let { testList ->
                        testList?.let { it1 -> roomsAdapter.submitList(it1) }
                    }
                }

                BaseResult.Status.ERROR -> {
                    binding.appLoader.hide()
                    binding.roomsRecyclerView.show()

//                    stylishToastyUtils.showErrorMessage("${it.message}")

                    Timber.e("${it.message}")


                }

                BaseResult.Status.LOADING -> {
                    binding.appLoader.show()
                    binding.roomsRecyclerView.hide()

//                    stylishToastyUtils.showInfoMessage("Loading...")

                }
            }

        })

    }

    private fun setUpList() {

        roomsAdapter = RoomsAdapter(this)
        binding.roomsRecyclerView.adapter = roomsAdapter
    }

    private fun fetchList() {

        homeViewModel.getRooms()
        binding.appLoader.show()

    }

    override fun onClick(view: View?) {

    }

    override fun onItemClick(key: String, item: Any) {


    }


}