package com.akomissarova.testmuseum.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akomissarova.testmuseum.R
import org.koin.android.ext.android.inject

class ArtCollectionsListFragment : Fragment() {

    companion object {
        fun newInstance() = ArtCollectionsListFragment()
    }

    private val viewModelFactory: ArtsCollectionsListViewModelFactory by inject()
    private val adapter: ArtCollectionsListAdapter by inject()
    private val viewModel: ArtCollectionsListViewModel = viewModelFactory.create(ArtCollectionsListViewModel::class.java)
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.art_collections_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.collections_list)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner, {
            adapter.update(it)
        })
    }

}