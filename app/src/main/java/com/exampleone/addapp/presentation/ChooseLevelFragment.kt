package com.exampleone.addapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.exampleone.addapp.databinding.FragmentChooseLevelBinding
import com.exampleone.addapp.domain.entity.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonLevelTest.setOnClickListener {
                launchGameFragment(Level.TEST)
            }
            buttonLevelEasy.setOnClickListener {
                launchGameFragment(Level.EASY)
            }
            buttonLevelNormal.setOnClickListener {
                launchGameFragment(Level.NORMAL)
            }
            buttonLevelHard.setOnClickListener {
                launchGameFragment(Level.HARD)
            }
            btEdEasy.setOnClickListener {
                launchEasyFragment()
            }
            btEdNormal.setOnClickListener {
                launchNormalFragment()
            }
            btEdHard.setOnClickListener {
                launchHardFragment()
            }
        }
    }

    private fun launchGameFragment(level: Level) {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level)
        )
    }

    private fun launchEasyFragment() {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToEasyFragment()
        )
    }

    private fun launchNormalFragment() {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToNormalFragment()
        )
    }

    private fun launchHardFragment() {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToHardFragment()
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}