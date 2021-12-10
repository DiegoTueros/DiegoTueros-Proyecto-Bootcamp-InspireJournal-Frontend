package com.nttdata.inspirationjournal2.view.inspirationdetail

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import com.nttdata.inspirationjournal2.databinding.FragmentInspirationDetailBinding
import com.nttdata.inspirationjournal2.InspirationApp
import com.nttdata.inspirationjournal2.MainActivity
import com.nttdata.inspirationjournal2.utlis.*
import com.nttdata.inspirationjournal2.view.formatDate
import com.nttdata.inspirationjournal2.model.InspirationItem
import com.nttdata.inspirationjournal2.view.addinspiration.AddInspirationActivity

class InspirationDetailFragment : Fragment() {

    companion object {
        const val INSPIRATION = "inspiracion"
    }

    private lateinit var binding: FragmentInspirationDetailBinding

    private val viewModel: InspirationDetailViewModel by viewModels {
        InsDetViewModelFactory(
            (requireActivity().application as InspirationApp).inspirationRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInspirationDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

         viewModel.viewState.observe(viewLifecycleOwner, Observer(::onViewStateChange))

        val inspiration = arguments?.getParcelable<InspirationItem>(INSPIRATION)

        inspiration?.let {
            viewModel.loadInspirationDetail(it.id.toString())
        }
    }

    private fun onViewStateChange(viewState: InspirationDetailViewState) {
        when (viewState){
            InspirationDetailViewState.Loading -> {
                hideError()
                showLoading()
            }
            is InspirationDetailViewState.InspirationDetailSuccess -> {
                hideLoading()
                hideError()
                showInspirationDetail(viewState.inspirationDetail)
            }
            InspirationDetailViewState.Error ->{
                hideLoading()
                showError()
            }

        }

    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        binding.progressBar.visibility = View.GONE
    }

    private fun showError() {
        binding.imageCategory.visibility = View.VISIBLE
    }

    private fun hideError() {
        binding.imageCategory.visibility = View.GONE
    }

    private fun showInspirationDetail(inspirationItem: InspirationItem) {
        binding.imageCategory.visibility = View.VISIBLE
        binding.backspace.setOnClickListener{
            val intent = Intent(requireActivity(), MainActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }

        with(inspirationItem){

            binding.title.text = title
            binding.description.text = description
            binding.date.text = formatDate(created_at.toString())
            binding.photo.load(URL)
            binding.imageCategory.setImageResource(imageInspirationDetail(category.icono))
            binding.title.setTextColor(Color.parseColor(category.color_primary))
            binding.date.setTextColor(Color.parseColor(category.color_primary))
            binding.description.setBackgroundResource(descriptionBackground(category.icono))
            binding.backspace.setImageResource(backspace(category.icono))
            binding.delete.setImageResource(delete(category.icono))

        }
    }

}