package com.strelkovax.catsapp.presentation.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.strelkovax.catsapp.R
import com.strelkovax.catsapp.databinding.FragmentDetailBinding
import com.strelkovax.catsapp.databinding.FragmentFavoriteBinding
import com.strelkovax.catsapp.databinding.FragmentListBinding
import com.strelkovax.catsapp.presentation.adapters.CatListAdapter
import com.strelkovax.catsapp.presentation.screens.detail.FragmentDetail
import com.strelkovax.catsapp.presentation.screens.main.ViewModelList

class FragmentFavorite : Fragment() {

    private val viewModel by viewModels<ViewModelFavorite>()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoriteBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setup() {
        setupRecyclerView()
        viewModel.loadSavedCats()
    }

    private fun setupRecyclerView() {
        val adapter = CatListAdapter()
        binding.rvCatsList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCatsList.adapter = adapter
        adapter.onCatItemClickListener = {
            parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(
                    R.id.fragment_container_view,
                    FragmentDetail.newInstance(it.id, it.url)
                )
                .addToBackStack("fragment-detail")
                .commit()
        }
        viewModel.catsImgList.observe(viewLifecycleOwner) {
            adapter.catList = it
        }
    }
}