package com.example.movielist.splash.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movielist.R
import com.example.movielist.databinding.FragmentSplashBinding
import com.example.movielist.utils.Constants.SPLASH_TIME_OUT
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToMoviesScreen()
    }

    private fun navigateToMoviesScreen() {
        lifecycleScope.launch {
            delay(SPLASH_TIME_OUT)
            findNavController().navigate(R.id.action_splashFragment_to_moviesFragment)
        }
    }
}