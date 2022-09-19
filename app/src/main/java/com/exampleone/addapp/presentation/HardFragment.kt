package com.exampleone.addapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.exampleone.addapp.databinding.FragmentHardBinding

class HardFragment : Fragment() {
     private var _binding: FragmentHardBinding? = null
    private val binding: FragmentHardBinding
        get() = _binding ?: throw RuntimeException("FragmentHardBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}