package com.nttdata.inspirationjournal2.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nttdata.inspirationjournal2.databinding.FragmentHomeBinding
import com.nttdata.inspirationjournal2.InspirationApp
import com.nttdata.inspirationjournal2.model.InspirationItem
import com.nttdata.inspirationjournal2.view.addinspiration.AddInspirationActivity
import com.nttdata.inspirationjournal2.view.inspirationdetail.DetailActivity
import com.nttdata.inspirationjournal2.view.inspirationdetail.InspirationDetailFragment


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var  adapter: InspirationItemAdapter

    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(
            (requireActivity().application as InspirationApp).inspirationRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = InspirationItemAdapter(mutableListOf(), ::onItemClick)
        binding.recyclerInspirationList.adapter = adapter
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(requireActivity(), AddInspirationActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::onViewStateChange))

        viewModel.loadInspirationList()
    }

    private fun onViewStateChange(viewState: HomeViewState) {
        when(viewState){
            HomeViewState.Loading -> {
                showLoading()
                Log.i("TAG", "HomeViewState.Loading")
            }
            is HomeViewState.HomeSuccess -> {
                hideLoading()
                showInspirationList(viewState.inspirationItem)
                Log.i("TAG", "HomeViewState.HomeSuccess")
            }
            HomeViewState.Error -> {
                Log.i("TAG", "HomeViewState.Error")
            }
        }

    }

    private fun showInspirationList(inspirationList: List<InspirationItem>){
        if(inspirationList.isEmpty()){
            binding.groupEmptyView.visibility = View.VISIBLE
        } else {
            binding.groupEmptyView.visibility = View.GONE
            adapter.addList(inspirationList)
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        binding.progressBar.visibility = View.GONE
    }


    private fun onItemClick(inspirationItem: InspirationItem){
        val bundle = Bundle()
        bundle.putParcelable(InspirationDetailFragment.INSPIRATION, inspirationItem)
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtras(bundle)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }

}