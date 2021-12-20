package com.akomissarova.testmuseum.artcollectionslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.cardview.widget.CardView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akomissarova.testmuseum.R
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListLoadingState
import com.akomissarova.testmuseum.artcollectionslist.ui.viewmodel.ArtCollectionsListViewModel
import com.akomissarova.testmuseum.artcollectionslist.ui.viewmodel.ArtsCollectionsListViewModelFactory
import org.koin.android.ext.android.inject

class ArtCollectionsListFragment : Fragment() {

    companion object {
        fun newInstance() = ArtCollectionsListFragment()
    }

    private val viewModelFactory: ArtsCollectionsListViewModelFactory by inject()
    private val adapter: ArtCollectionsListAdapter by inject()
    private val viewModel: ArtCollectionsListViewModel = viewModelFactory.create(
        ArtCollectionsListViewModel::class.java)
    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var errorView: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.art_collections_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorView = view.findViewById(R.id.collectionsListErrorView)
        progress = view.findViewById(R.id.collectionsListLoadingProgress)
        recyclerView = view.findViewById(R.id.collectionsList)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showInitialState()

        viewModel.data.observe(viewLifecycleOwner, {
            when (it) {
                is ArtCollectionsListLoadingState.Success -> {
                    showSuccessState(it)
                }
                else -> {
                    showErrorState()
                }
            }
        })
    }

    private fun showSuccessState(it: ArtCollectionsListLoadingState.Success) {
        adapter.update(it.result)
        errorView.isGone = true
        recyclerView.isVisible = true
        progress.isGone = true
    }

    private fun showInitialState() {
        recyclerView.isGone = true
        progress.isVisible = true
        errorView.isGone = true
    }

    private fun showErrorState() {
        errorView.isVisible = true
        progress.isGone = true
        recyclerView.isGone = true
    }
}