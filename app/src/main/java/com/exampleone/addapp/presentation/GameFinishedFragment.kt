package com.exampleone.addapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.exampleone.addapp.R
import com.exampleone.addapp.data.database.DbManager
import com.exampleone.addapp.data.model.Articles
import com.exampleone.addapp.databinding.FragmentGameFinishedBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()
    private val art = Articles()
    lateinit var dbManager: DbManager

    private val easyLink = "https://school.skysmart.ru/"
    private val normalLink = "https://budu5.com/"
    private val hardLink = "https://urokmatematiki.ru/"

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)

        dbManager = DbManager()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        binding.gameResult = args.gameResult

        binding.btAdEasy.setOnClickListener {
            if (binding.btAdEasy.text == resources.getString(R.string.easy_level)) {
                binding.btAdEasy.text = "Отменить"
                dbManager.publishArtEasy(fillArtWithEasy())
                Toast.makeText(activity, "Матераилы добавлены", Toast.LENGTH_LONG).show()
            } else {
                binding.btAdEasy.text = resources.getString(R.string.easy_level)
                clearEasyFromDb()
                Toast.makeText(activity, "Матераилы удалены", Toast.LENGTH_LONG).show()
            }

        }

        binding.btAdNormal.setOnClickListener {
            if (binding.btAdNormal.text == resources.getString(R.string.normal_level)) {
                binding.btAdNormal.text = "Отменить"
                dbManager.publishArtNormal(fillArtWithNormal())
                Toast.makeText(activity, "Матераилы добавлены", Toast.LENGTH_LONG).show()
            } else {
                binding.btAdNormal.text = resources.getString(R.string.normal_level)
                clearNormFromDb()
                Toast.makeText(activity, "Матераилы удалены", Toast.LENGTH_LONG).show()
            }
        }


        binding.btAdHard.setOnClickListener {
            if (binding.btAdHard.text == resources.getString(R.string.hard_level)) {
                binding.btAdHard.text = "Отменить"
                dbManager.publishArtHard(fillArtWithHard())
                Toast.makeText(activity, "Матераилы добавлены", Toast.LENGTH_LONG).show()
            } else {
                binding.btAdHard.text = resources.getString(R.string.hard_level)
                clearHardFromDb()
                Toast.makeText(activity, "Матераилы удалены", Toast.LENGTH_LONG).show()
            }
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

    private fun clearEasyFromDb() {
        dbManager.db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.ref.child("artEasy").removeValue()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun clearNormFromDb() {
        dbManager.db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.ref.child("artNormal").removeValue()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun clearHardFromDb() {
        dbManager.db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.ref.child("artHard").removeValue()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


    private fun fillArtWithEasy(): Articles {
        art.easyArt = easyLink
        return art
    }


    private fun fillArtWithNormal(): Articles {
        art.normalArt = normalLink
        return art
    }

    private fun fillArtWithHard(): Articles {
        art.hardArt = hardLink
        return art
    }
}