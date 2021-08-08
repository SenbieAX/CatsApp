package com.strelkovax.catsapp.presentation.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.strelkovax.catsapp.R
import com.strelkovax.catsapp.databinding.FragmentListBinding
import com.strelkovax.catsapp.presentation.adapters.CatListAdapter
import com.strelkovax.catsapp.presentation.SingleToast
import com.strelkovax.catsapp.presentation.screens.detail.FragmentDetail

class FragmentList : Fragment() {

    private val viewModel by activityViewModels<ViewModelList>()

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
        setupRecyclerView()
        viewModel.page.observe(viewLifecycleOwner) {
            binding.textViewPage.text = it.toString()
            if (it == 1) {
                binding.imgArrowLeft.visibility = View.INVISIBLE
            } else {
                binding.imgArrowLeft.visibility = View.VISIBLE
            }
        }
        viewModel.errors.observe(viewLifecycleOwner) {
            if (it != null) {
                SingleToast.show(requireContext(), it.message, Toast.LENGTH_LONG)
                viewModel.clearErrors()
            }
        }
        viewModel.loadData()
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

    private fun setupButtons() {
        binding.imgArrowLeft.setOnClickListener {
            if (viewModel.isOnline()) {
                viewModel.backPage()
                binding.rvCatsList.smoothScrollToPosition(0)
            }
        }
        binding.imgArrowRight.setOnClickListener {
            if (viewModel.isOnline()) {
                viewModel.nextPage()
                binding.rvCatsList.smoothScrollToPosition(0)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}