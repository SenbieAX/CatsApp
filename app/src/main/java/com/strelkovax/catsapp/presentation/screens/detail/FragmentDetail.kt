package com.strelkovax.catsapp.presentation.screens.detail

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import com.strelkovax.catsapp.R
import com.strelkovax.catsapp.databinding.FragmentDetailBinding
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.presentation.SingleToast

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
        viewModel.back.observe(viewLifecycleOwner) {
            if (it == true) {
                parentFragmentManager.popBackStack()
            }
        }
        viewModel.errors.observe(viewLifecycleOwner) {
            if (it != null) {
                SingleToast.show(requireContext(), it.message, Toast.LENGTH_LONG)
                viewModel.clearErrors()
            }
        }
        viewModel.initFavorite(catItem)
    }

    private fun setupButtons() {
        binding.buttonAddToFavorite.setOnClickListener {
            viewModel.changeFavorite(catItem)
        }
        binding.buttonDownload.setOnClickListener {
            if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE)
            ) if (viewModel.isOnline()) viewModel.downloadImage(catId, catUrl)
        }
    }

    companion object {

        private const val STORAGE_PERMISSION_CODE = 101

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


    private fun checkPermission(permission: String, requestCode: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) return true
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
            return false
        }
        return true
    }
}