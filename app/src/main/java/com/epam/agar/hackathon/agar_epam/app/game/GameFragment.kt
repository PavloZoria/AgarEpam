package com.epam.agar.hackathon.agar_epam.app.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.epam.agar.hackathon.agar_epam.app.util.collectWhenResumed
import com.epam.agar.hackathon.agar_epam.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // lifecycleScope.launch {
        //     GameEngine.startGame("testRoom1")
        //     delay(20_000)
        //     GameEngine.stopGame()
        // }
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(GameViewModel::class.java)
        setUpView()
        setUpViewModel()
    }

    private fun setUpView() = with(binding) {
        editText.addTextChangedListener {
            val string = it.toString()
            viewModel.roomIdChanged(string)
        }
        startGameButton.setOnClickListener {
            viewModel.startGame()
        }
        stopGameButton.setOnClickListener {
            viewModel.stopGame()
        }
    }

    private fun setUpViewModel() = with(viewModel) {
        startGameEnabled.collectWhenResumed(lifecycleScope) {
            binding.startGameButton.isEnabled = it
            binding.startGameButton.isClickable = it
        }
    }

    companion object {
        fun newInstance() = GameFragment()
    }
}