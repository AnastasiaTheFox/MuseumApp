package com.akomissarova.testmuseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.akomissarova.testmuseum.artcollectionslist.ui.ArtCollectionsListFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ArtCollectionsListFragment>(R.id.fragment_container)
            }
        }

    }
}