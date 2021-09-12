package com.atimi.jokeapp.ui.joke

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.atimi.jokeapp.R
import com.atimi.jokeapp.databinding.FragmentJokeBinding
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Display a joke.
 */
@AndroidEntryPoint
class JokeFragment : Fragment(), Toolbar.OnMenuItemClickListener,
    NavigationBarView.OnItemSelectedListener {

    private val viewModel: JokeViewModel by viewModels()
    private lateinit var binding: FragmentJokeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJokeBinding.inflate(
            inflater, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        binding.toolbar.setOnMenuItemClickListener(this)
        binding.navBottom.setOnItemSelectedListener(this)

        viewModel.data.observe(viewLifecycleOwner) {
            binding.joke = it
            updateToolbar()
        }

        return binding.root
    }

    private fun updateToolbar() {
        binding.toolbar.menu.forEach {
            it.isVisible = when (it.itemId) {
                R.id.action_remove ->
                    viewModel.isFavourite
                R.id.action_add ->
                    !viewModel.isFavourite
                R.id.action_share ->
                    viewModel.data.value != null
                else -> false
            }
        }
    }

    override fun onMenuItemClick(it: MenuItem): Boolean {
        when (it.itemId) {
            R.id.action_add -> {
                viewModel.addJokeToFavourites()
                Snackbar.make(
                    binding.root,
                    R.string.favourite_added,
                    Snackbar.LENGTH_SHORT
                ).show()
                return true
            }
            R.id.action_remove -> {
                viewModel.removeJokeFromFavourites()
                Snackbar.make(
                    binding.root,
                    R.string.favourite_removed,
                    Snackbar.LENGTH_SHORT
                ).show()

                return true
            }
            R.id.action_share -> {
                shareJoke()
            }
        }
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.refreshJoke()
                return true
            }
            R.id.action_favourites -> {
                findNavController().navigate(R.id.action_jokeFragment_to_jokesFragment)
                return true
            }
        }

        return false
    }

    private fun shareJoke() {
        viewModel.data.observe(viewLifecycleOwner) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, it.joke)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, getString(R.string.action_share))
            startActivity(shareIntent)
        }
    }
}