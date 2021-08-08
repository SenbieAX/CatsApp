package com.strelkovax.catsapp.presentation.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import com.strelkovax.catsapp.R
import com.strelkovax.catsapp.databinding.FragmentDetailBinding
import com.strelkovax.catsapp.domain.entity.CatItem

private const val ARG_CAT_ID = "cat_id"
private const val ARG_CAT_URL = "cat_url"

class FragmentDetail : Fragment() {

    private val viewModel by viewModels<ViewModelDetail>()

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailsBinding == null")

    private var catId: String = ""
    private var catUrl: String = ""

    private var catItem = CatItem("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            catId = it.getString(ARG_CAT_ID).toString()
            catUrl = it.getString(ARG_CAT_URL).toString()
            catItem.id = catId
            catItem.url = catUrl
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        setupButtons()
        Picasso.get().load(catUrl).placeholder(R.drawable.img_placeholder)
            .into(binding.imageViewCat)
        viewModel.text.observe(viewLifecycleOwner) {
            binding.buttonAddToFavorite.text = it
        }
        viewModel.initFavorite(catItem)
    }

    private fun setupButtons() {
        binding.buttonAddToFavorite.setOnClickListener {
            viewModel.changeFavorite(catItem)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(catId: String, catUrl: String) =
            FragmentDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_CAT_ID, catId)
                    putString(ARG_CAT_URL, catUrl)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}