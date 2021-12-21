package com.akomissarova.testmuseum.artcollectionslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.cardview.widget.CardView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akomissarova.testmuseum.R
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListLoadingState
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListViewItem
import com.akomissarova.testmuseum.artcollectionslist.ui.viewmodel.ArtCollectionsListViewModel
import com.akomissarova.testmuseum.artcollectionslist.ui.viewmodel.ArtsCollectionsListViewModelFactory
import org.koin.android.ext.android.inject

class ArtCollectionsListFragment : Fragment() {

    companion object {
        fun newInstance() = ArtCollectionsListFragment()
    }

    private val viewModelFactory: ArtsCollectionsListViewModelFactory by inject()
    private val adapter: ArtCollectionsListAdapter by inject()
    lateinit var viewModel: ArtCollectionsListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var errorView: CardView
    private lateinit var refresh: ImageButton

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
        refresh = view.findViewById(R.id.collectionsListRefresh)
        recyclerView = view.findViewById(R.id.collectionsList)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter

        refresh.setOnClickListener {
            viewModel.refresh()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //ideally needs to be reworked to newer callbacks
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArtCollectionsListViewModel::class.java)
        viewModel.items.observe(viewLifecycleOwner, {
            when (it) {
                is ArtCollectionsListLoadingState.Success -> {
                    showSuccessState(it.result)
                }
                is ArtCollectionsListLoadingState.Progress -> {
                    showLoadingState()
                }
                else -> {
                    showErrorState()
                }
            }
        })
    }

    private fun showSuccessState(list: List<ArtCollectionsListViewItem>) {
        adapter.update(list)
        errorView.isGone = true
        recyclerView.isVisible = true
        progress.isGone = true
    }

    private fun showLoadingState() {
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
