package com.exampleone.addapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.exampleone.addapp.databinding.FragmentNormalBinding


class NormalFragment : Fragment() {
    private var _binding: FragmentNormalBinding? = null
    private val binding: FragmentNormalBinding
        get() = _binding ?: throw RuntimeException("FragmentNormalBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNormalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}