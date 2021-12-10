package com.nttdata.inspirationjournal2.view.addinspiration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nttdata.inspirationjournal2.InspirationApp
import com.nttdata.inspirationjournal2.MainActivity
import com.nttdata.inspirationjournal2.databinding.FragmentAddInspirationBinding
import kotlinx.android.synthetic.main.fragment_add_inspiration.*

class AddInspirationFragment : Fragment() {

    private lateinit var binding: FragmentAddInspirationBinding

    private val viewModel: AddInspirationViewModel by viewModels {
        AddInspirationViewModelFactory(
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
        binding = FragmentAddInspirationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backspace.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
        binding.spinner
        val list = listOf("Música", "Dibujo", "Poema", "Fotografía")
        val adapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_dropdown_item, list)
        var category: String = ""
        spinner.adapter = adapter

        spinner.onItemSelectedListener  = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selected = list[position]
                when(selected){
                    "Música" -> category = "61b03bcf4c3f1265ee67d26c"
                    "Dibujo" -> category = "61b03bcf4c3f1265ee67d26d"
                    "Poema" -> category = "61b03bcf4c3f1265ee67d26e"
                    "Fotografía" -> category = "61b03bcf4c3f1265ee67d26f"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::onViewStateChange))

        binding.save.setOnClickListener{
            val title = binding.title!!.text.toString()
            val description = binding.description!!.text.toString()

            viewModel.saveInspiration(title, description, category)

        }
    }

    private fun onViewStateChange(viewState: AddInspirationViewState) {

        when (viewState){
            AddInspirationViewState.Loading -> {

            }
            is AddInspirationViewState.AddInspirationSucces -> {
                val status = viewState.status
                if (status){
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    requireActivity().startActivity(intent)
                    Toast.makeText(requireContext(), "Se agrego su inspiración", Toast.LENGTH_LONG)
                        .show()
                    requireActivity().finish()
                }else {
                    Toast.makeText(requireContext(), "Ocurrió un error al guardar", Toast.LENGTH_LONG)
                        .show()
                }

            }
            AddInspirationViewState.Error ->{
                Toast.makeText(requireContext(), "Ocurrió un error al guardar", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

}