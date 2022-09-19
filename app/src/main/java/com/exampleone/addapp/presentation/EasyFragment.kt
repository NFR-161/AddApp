package com.exampleone.addapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.exampleone.addapp.databinding.FragmentEasyBinding


class EasyFragment : Fragment() {


    private var _binding: FragmentEasyBinding? = null
    private val binding: FragmentEasyBinding
        get() = _binding ?: throw RuntimeException("FragmentEasyBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEasyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
