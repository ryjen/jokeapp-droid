package com.atimi.jokeapp.ui.jokes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.atimi.jokeapp.R
import com.atimi.jokeapp.databinding.FragmentJokesBinding
import com.atimi.jokeapp.model.Joke
import dagger.hilt.android.AndroidEntryPoint


/**
 * Display a list of favourite jokes
 */
@AndroidEntryPoint
class JokesFragment : Fragment() {

    private lateinit var binding: FragmentJokesBinding
    private val viewModel: JokesViewModel by viewModels()

    private val adapter = JokesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJokesBinding.inflate(inflater, container, false)

        adapter.callback = object: JokeAdapterCallback{
            override fun onRemove(pos: Int) {
                viewModel.removeJoke(pos)
                adapter.notifyItemRemoved(pos)
            }

            override fun onShare(joke: Joke) {
                shareJoke(joke)
            }
        }

        binding.apply {
            list.adapter = adapter
            toolbar.title = getString(R.string.action_favourites)

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }

        viewModel.data.observe(viewLifecycleOwner) { jokes ->
            binding.loaded = true
            binding.hasJokes = jokes.isNotEmpty()
            adapter.submitList(jokes)
        }

        ItemTouchHelper(
            DeleteItemCallback(
                binding.root,
                viewModel
            )
        ).attachToRecyclerView(binding.list)

        return binding.root
    }

    private fun shareJoke(joke: Joke) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, joke.joke)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, getString(R.string.action_share))
        startActivity(shareIntent)

    }
}
