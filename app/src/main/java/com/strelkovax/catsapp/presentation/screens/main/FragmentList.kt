package com.strelkovax.catsapp.presentation.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.strelkovax.catsapp.databinding.FragmentListBinding
import com.strelkovax.catsapp.presentation.adapters.CatListAdapter

class FragmentList : Fragment() {

    private lateinit var viewModel: ViewModelList

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding ?: throw RuntimeException("FragmentListBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        setupButtons()
        val adapter = CatListAdapter()
        binding.rvCatsList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCatsList.adapter = adapter
        viewModel = ViewModelProvider(this)[ViewModelList::class.java]
        viewModel.catsImgList.observe(viewLifecycleOwner) {
            adapter.catList = it
        }
        viewModel.page.observe(viewLifecycleOwner) {
            binding.rvCatsList.smoothScrollToPosition(0)
            binding.textViewPage.text = it.toString()
            if (it == 1) {
                binding.imgArrowLeft.visibility = View.INVISIBLE
            } else {
                binding.imgArrowLeft.visibility = View.VISIBLE
            }
        }
        viewModel.loadData()
    }

    private fun setupButtons() {
        binding.imgArrowLeft.setOnClickListener {
            viewModel.backPage()
        }
        binding.imgArrowRight.setOnClickListener {
            viewModel.nextPage()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}