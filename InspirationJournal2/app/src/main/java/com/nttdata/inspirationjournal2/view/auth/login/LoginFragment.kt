
package com.nttdata.inspirationjournal2.view.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nttdata.inspirationjournal2.InspirationApp
import com.nttdata.inspirationjournal2.MainActivity
import com.nttdata.inspirationjournal2.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
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
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::onViewStateChange))
        binding.buttonLogin.setOnClickListener {
            val email = binding.inputEmail.editText!!.text.toString()
            val password = binding.inputPassword.editText!!.text.toString()

            viewModel.login(email, password)

        }
    }

    private fun onViewStateChange(viewState: LoginViewState) {

        when (viewState){
            LoginViewState.Loading ->{

            }
            is LoginViewState.AuthSuccess -> {
                val name = viewState.auth.user?.username
                val status = viewState.auth.status
                status?.let {
                    if (status){
                        val bundle = Bundle()
                        bundle.putString("name", name)
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        intent.putExtras(bundle)
                        requireActivity().startActivity(intent)
                        Toast.makeText(requireContext(), "Bienvenido $name", Toast.LENGTH_LONG)
                            .show()
                        requireActivity().finish()
                    } else {
                        Toast.makeText(requireContext(), viewState.auth.message, Toast.LENGTH_LONG)
                            .show()
                    }
                }

            }
            LoginViewState.Error -> {

            }
        }

    }
}