package com.app.mymainapp.ui.home

import android.annotation.SuppressLint
import android.app.Notification.EXTRA_PEOPLE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.SearchView.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.app.mymainapp.R
import com.app.mymainapp.baseresult.BaseResult
import com.app.mymainapp.databinding.FragmentPeopleBinding
import com.app.mymainapp.listeners.OnItemClickListener
import com.app.mymainapp.models.PeopleResponseItem
import com.app.mymainapp.ui.adapters.PeopleListAdapter
import com.app.mymainapp.ui.peopleDetail.PeopleDetailActivity
import com.app.mymainapp.utils.StylishToastyUtils
import com.app.mymainapp.utils.hide
import com.app.mymainapp.utils.hideKeyboard
import com.app.mymainapp.utils.show
import com.app.mymainapp.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class PeopleFragment : Fragment(R.layout.fragment_people), View.OnClickListener,
    OnItemClickListener {


    private val binding: FragmentPeopleBinding by viewBinding()
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var peopleAdapter: PeopleListAdapter

    private var peopleList: ArrayList<PeopleResponseItem> = arrayListOf()
    private var peopleSearchList: ArrayList<PeopleResponseItem> = arrayListOf()


    @Inject
    lateinit var stylishToastyUtils: StylishToastyUtils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = this

        setUpList()
        fetchList()
        observeList()
        setSearchView()
        setupSwipe()
    }

    private fun setupSwipe() {

        binding.swipeLayout.setOnRefreshListener {

            binding.textViewRetry.visibility = GONE
            fetchList()
        }
    }


    private fun observeList() {

        homeViewModel.res.observe(viewLifecycleOwner, Observer {

            when (it.status) {
                BaseResult.Status.SUCCESS -> {

//                    stylishToastyUtils.showSuccessMessage("success")

                    binding.appLoader.hide()
                    binding.peopleRecyclerView.show()
                    binding.textViewRetry.visibility = GONE
                    binding.swipeLayout.isRefreshing=false
                    // it.data?.let { it1 -> peopleAdapter.submitList(it1) }
                    peopleList.addAll(it.data!!)
                    peopleSearchList.addAll(it.data!!)
                    peopleAdapter.submitList(peopleList)
                }

                BaseResult.Status.ERROR -> {
                    binding.appLoader.hide()
                    binding.peopleRecyclerView.show()
                    binding.swipeLayout.isRefreshing=false
                    if (it.message.equals("No internet")) {

                        binding.peopleRecyclerView.hide()
                        binding.textViewRetry.visibility = VISIBLE
                    }
                    stylishToastyUtils.showErrorMessage("${it.message}")

                    Timber.e("${it.message}")


                }

                BaseResult.Status.LOADING -> {
                    binding.appLoader.show()
                    binding.textViewRetry.visibility = GONE
                    binding.peopleRecyclerView.hide()
                    binding.swipeLayout.isRefreshing=false
//                    stylishToastyUtils.showInfoMessage("Loading...")

                }
            }

        })

    }


    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch(Dispatchers.Main) {
                    delay(200)
                    if (newText?.isNotEmpty() == true) {
                        filterPeople(newText ?: "")
                    } else {

                        binding.peopleRecyclerView.show()
                        binding.layoutNoPeopleFound.hide()
                        peopleAdapter.submitList(arrayListOf())
                        peopleAdapter.submitList(peopleList.map { it.copy() })
                        //peopleAdapter.notifyDataSetChanged()
                        binding.peopleRecyclerView.layoutManager?.smoothScrollToPosition(
                            binding.peopleRecyclerView,
                            null,
                            0
                        )

                    }
                }
                return false
            }
        })


    }

    private fun filterPeople(text: String) {
        val filterList: ArrayList<PeopleResponseItem> = ArrayList()
        for (item in peopleList) {
            if ((item.firstName?.lowercase()?.contains(text.lowercase()) == true) ||
                (item.lastName?.lowercase()?.contains(text.lowercase()) == true)
            ) {
                filterList.add(item)
            }
        }
        if (filterList.size > 0) {
            binding.peopleRecyclerView.show()
            binding.layoutNoPeopleFound.hide()
            peopleAdapter.submitList(arrayListOf())
            peopleAdapter.submitList(filterList.map { it.copy() })
            binding.peopleRecyclerView.layoutManager?.smoothScrollToPosition(
                binding.peopleRecyclerView,
                null,
                0
            )
        } else {
            binding.peopleRecyclerView.hide()
            binding.layoutNoPeopleFound.show()
        }
    }

    private fun setUpList() {
        peopleAdapter = PeopleListAdapter(this)
        binding.peopleRecyclerView.adapter = peopleAdapter
    }

    private fun fetchList() {
        binding.textViewRetry.visibility = GONE
        homeViewModel.getPeople()
        binding.appLoader.show()
    }

    override fun onClick(view: View?) {

        when (view) {
            binding.textViewRetry -> fetchList()
        }
    }

    override fun onItemClick(key: String, item: Any) {

        val peopleModel = item as PeopleResponseItem

        val intent = Intent(context, PeopleDetailActivity::class.java)

        intent.putExtra("item", item)
        startActivity(intent)
    }

}
