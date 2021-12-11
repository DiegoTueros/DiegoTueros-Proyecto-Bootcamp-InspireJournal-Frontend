package com.nttdata.inspirationjournal2.view.favorites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nttdata.inspirationjournal2.R
import com.nttdata.inspirationjournal2.databinding.FragmentFavoritesBinding
import com.nttdata.inspirationjournal2.InspirationApp
import com.nttdata.inspirationjournal2.databinding.FragmentHomeBinding
import com.nttdata.inspirationjournal2.model.InspirationItem
import com.nttdata.inspirationjournal2.view.inspirationdetail.DetailActivity
import com.nttdata.inspirationjournal2.view.inspirationdetail.InspirationDetailFragment


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoritesItemAdapter

    private val viewModel by viewModels<FavoritesViewModel> {
        FavoritesViewModelFactory(
            (requireActivity().application as InspirationApp).inspirationRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FavoritesItemAdapter(mutableListOf(), ::onItemClick)
        binding.recyclerFavoritesList.adapter = adapter

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::onViewStateChange))

        viewModel.loadFavoritesList()
    }
    private fun onViewStateChange(viewState: FavoritesViewState) {
        when(viewState){
            FavoritesViewState.Loading -> {
                showLoading()
                Log.i("TAG", "HomeViewState.Loading")
            }
            is FavoritesViewState.FavoriteSuccess -> {
                hideLoading()
                showInspirationList(viewState.inspirationItem)
                Log.i("TAG", "HomeViewState.HomeSuccess")
            }
            FavoritesViewState.Error -> {
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