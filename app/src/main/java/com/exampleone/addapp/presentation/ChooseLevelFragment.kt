package com.exampleone.addapp.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.exampleone.addapp.data.database.DbManager
import com.exampleone.addapp.databinding.FragmentChooseLevelBinding
import com.exampleone.addapp.domain.entity.Level
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    private val dbManager = DbManager()


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

                if (!isSIMInserted(requireActivity()) ||
                    getIso() == "us" ||
                    getIso() == "in" ||
                    !isConnectedToInternet()
                ) {

                    launchEasyFragment()
                } else {
                    openEasyLink()
                }

            }
            btEdNormal.setOnClickListener {
                if (!isSIMInserted(requireActivity()) ||
                    getIso() == "us" ||
                    getIso() == "in" ||
                    !isConnectedToInternet()
                ) {

                    launchNormalFragment()
                } else {
                    openNormalLink()
                }

            }
            btEdHard.setOnClickListener {
                if (!isSIMInserted(requireActivity()) ||
                    getIso() == "us" ||
                    getIso() == "in" ||
                    !isConnectedToInternet()
                ) {

                    launchHardFragment()
                } else {
                    openHardLink()
                }

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

    private fun isSIMInserted(context: Context): Boolean {
        return TelephonyManager.SIM_STATE_ABSENT != (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).simState
    }

    private fun getIso(): String {
        val tMgr =
            activity?.getSystemService(AppCompatActivity.TELEPHONY_SERVICE) as TelephonyManager
        return tMgr.simCountryIso
    }


    private fun openEasyLink() {
        dbManager.db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val easyLink: String? = snapshot.child("artEasy").value as String?
                if (easyLink != null) {
                    val builder = CustomTabsIntent.Builder()
                    builder.setColorScheme(CustomTabsIntent.COLOR_SCHEME_LIGHT)
                    builder.setStartAnimations(
                        requireActivity(),
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                    val intent = builder.build()
                    intent.launchUrl(requireActivity(), Uri.parse(easyLink))
                } else {
                    launchEasyFragment()
                }

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun openNormalLink() {
        dbManager.db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val normalLink: String? = snapshot.child("artNormal").value as String?
                if (normalLink != null) {
                    val builder = CustomTabsIntent.Builder()
                    builder.setColorScheme(CustomTabsIntent.COLOR_SCHEME_LIGHT)
                    builder.setStartAnimations(
                        requireActivity(),
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                    val intent = builder.build()
                    intent.launchUrl(requireActivity(), Uri.parse(normalLink))
                } else {
                    launchNormalFragment()
                }

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


    private fun openHardLink() {
        dbManager.db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val hardLink: String? = snapshot.child("artHard").value as String?
                if (hardLink != null) {
                    val builder = CustomTabsIntent.Builder()
                    builder.setColorScheme(CustomTabsIntent.COLOR_SCHEME_LIGHT)
                    builder.setStartAnimations(
                        requireActivity(),
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                    val intent = builder.build()
                    intent.launchUrl(requireActivity(), Uri.parse(hardLink))
                } else {
                    launchHardFragment()
                }

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun isConnectedToInternet(): Boolean {
        val cm =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }


}