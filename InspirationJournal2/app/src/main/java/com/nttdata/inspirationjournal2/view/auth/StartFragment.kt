package com.nttdata.inspirationjournal2.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nttdata.inspirationjournal2.R
import com.nttdata.inspirationjournal2.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonLogin.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }
        binding.buttonLogup.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_registerFragment)
        }
    }

}