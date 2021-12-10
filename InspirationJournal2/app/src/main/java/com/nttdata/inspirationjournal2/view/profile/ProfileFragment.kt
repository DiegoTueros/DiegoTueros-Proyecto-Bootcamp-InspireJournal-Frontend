package com.nttdata.inspirationjournal2.view.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nttdata.inspirationjournal2.R
import com.nttdata.inspirationjournal2.databinding.FragmentProfileBinding
import com.nttdata.inspirationjournal2.view.addinspiration.AddInspirationActivity
import com.nttdata.inspirationjournal2.view.auth.AuthActivity


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val name = arguments?.getString("name")
        binding.name.text = name

        binding.buttonLogout.setOnClickListener{
            val intent = Intent(requireActivity(), AuthActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
    }

}