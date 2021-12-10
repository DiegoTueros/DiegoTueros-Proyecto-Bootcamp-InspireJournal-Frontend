package com.nttdata.inspirationjournal2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.nttdata.inspirationjournal2.databinding.ActivityMainBinding
import com.nttdata.inspirationjournal2.model.InspirationItem
import com.nttdata.inspirationjournal2.view.favorites.FavoritesFragment
import com.nttdata.inspirationjournal2.view.home.HomeFragment
import com.nttdata.inspirationjournal2.view.inspirationdetail.InspirationDetailFragment
import com.nttdata.inspirationjournal2.view.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private lateinit var activeFragment: Fragment
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val bundle: Bundle? = getIntent().getExtras()
        val name: String? = bundle?.getString("name")

        val args = Bundle()
        args.putString("name", name)

        setupBottomNav(args)

    }

    private fun setupBottomNav(args: Bundle){
        fragmentManager = supportFragmentManager

        val homeFragment = HomeFragment()
        val favoritesFragment = FavoritesFragment()
        val profileFragment = ProfileFragment()
        profileFragment.arguments = args

        activeFragment = homeFragment


        fragmentManager.beginTransaction()
            .add(R.id.hostFragment, profileFragment, ProfileFragment::class.java.name)
            .hide(profileFragment)
            .commit()
        fragmentManager.beginTransaction()
            .add(R.id.hostFragment, favoritesFragment, FavoritesFragment::class.java.name)
            .hide(favoritesFragment)
            .commit()
        fragmentManager.beginTransaction()
            .add(R.id.hostFragment, homeFragment, HomeFragment::class.java.name)
            .commit()

        binding.buttomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.action_home -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                    activeFragment = homeFragment
                    true
                }
                R.id.action_favorites -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(favoritesFragment).commit()
                    activeFragment = favoritesFragment
                    true
                }
                R.id.action_profile -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit()
                    activeFragment = profileFragment
                    true
                }
                else -> false
            }
        }

    }
}