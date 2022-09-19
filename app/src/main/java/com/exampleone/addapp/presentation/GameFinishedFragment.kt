package com.exampleone.addapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.exampleone.addapp.data.database.DbManager
import com.exampleone.addapp.data.model.Articles
import com.exampleone.addapp.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

//    lateinit var dbManager: DbManager

    val easyLink = "https://school.skysmart.ru/"
    val normalLink = "https://budu5.com/"
    val hardLink = "https://urokmatematiki.ru/"

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
//        dbManager = DbManager()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        binding.gameResult = args.gameResult

        binding.btAdEasy.setOnClickListener {
//            dbManager?.publishArt(fillArtWithEasy())
        }
    }

    private fun setupClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }


    private fun fillArtWithEasy(): Articles {
        val art: Articles
        binding.apply {
            art = Articles(
                easyLink,
                null,
                null
            )
        }
        return art
    }
}