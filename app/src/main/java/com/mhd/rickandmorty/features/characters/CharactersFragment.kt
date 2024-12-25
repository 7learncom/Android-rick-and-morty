package com.mhd.rickandmorty.features.characters

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.mhd.rickandmorty.R
import com.mhd.rickandmorty.databinding.FragmentCharactersBinding
import com.mhd.rickandmorty.features.CommonLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding
        get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels()

    private val adapter = CharactersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharactersBinding.bind(view)
        binding.btnRetry.setOnClickListener { adapter.retry() }
        setupAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                launch {
                    viewModel.characters.collect {
                        adapter.submitData(it)
                    }
                }

                launch {
                    adapter.loadStateFlow.map { it.refresh }.collectLatest {
                        binding.progressCircular.isVisible = it is LoadState.Loading
                        binding.llError.isVisible = it is LoadState.Error
                        binding.rvCharacters.isVisible = it is LoadState.NotLoading

                        if (it is LoadState.Error) {
                            binding.tvErrorMessage.text = it.error.message
                        }
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.rvCharacters.adapter = adapter.withLoadStateFooter(
            footer = CommonLoadStateAdapter(adapter::retry)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}