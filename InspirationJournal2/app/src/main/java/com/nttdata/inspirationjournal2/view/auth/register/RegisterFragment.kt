package com.nttdata.inspirationjournal2.view.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nttdata.inspirationjournal2.InspirationApp
import com.nttdata.inspirationjournal2.MainActivity
import com.nttdata.inspirationjournal2.R
import com.nttdata.inspirationjournal2.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(
            (requireActivity().application as InspirationApp).authRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::onViewStateChange))
        binding.buttonRegister.setOnClickListener{
            val username = binding.inputUser.editText!!.text.toString()
            val email = binding.inputEmail.editText!!.text.toString()
            val password = binding.inputPassword.editText!!.text.toString()

            viewModel.register(username, email, password)

        }
    }

    private fun onViewStateChange(viewState: RegisterViewState) {

        when(viewState){
            RegisterViewState.Loading -> {

            }
            is RegisterViewState.AuthSuccess -> {
                val status =  viewState.auth.status
                status?.let {
                    if (status){
                        findNavController().navigate(R.id.action_registerFragment_to_startFragment)
                        Toast.makeText(requireContext(), viewState.auth.message, Toast.LENGTH_LONG)
                            .show()
                        onDestroyView()
                    }else {
                        Toast.makeText(requireContext(), viewState.auth.message, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

            RegisterViewState.Error -> {
                Toast.makeText(requireContext(), "Ups, Error del servidor", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

}