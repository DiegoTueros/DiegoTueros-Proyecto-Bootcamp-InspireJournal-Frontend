package com.nttdata.inspirationjournal2.view.inspirationdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.nttdata.inspirationjournal2.R
import com.nttdata.inspirationjournal2.model.InspirationItem
import com.nttdata.inspirationjournal2.view.inspirationdetail.InspirationDetailFragment.Companion.INSPIRATION

class DetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle: Bundle? = getIntent().getExtras()
        val inspirationItem: InspirationItem? = bundle?.getParcelable<InspirationItem>(InspirationDetailFragment.INSPIRATION)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = InspirationDetailFragment()

        val args = Bundle()
        args.putParcelable(INSPIRATION, inspirationItem)
        fragment.arguments = args
        fragmentTransaction.add(R.id.fragment_container_detail, fragment).commit()


    }
}