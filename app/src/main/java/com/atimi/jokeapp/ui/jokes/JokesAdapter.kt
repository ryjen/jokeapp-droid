package com.atimi.jokeapp.ui.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atimi.jokeapp.R
import com.atimi.jokeapp.databinding.ListItemJokeBinding
import com.atimi.jokeapp.model.Joke

interface JokeAdapterCallback {
    fun onRemove(pos: Int)
    fun onShare(joke: Joke)
}

class JokesAdapter :
    ListAdapter<Joke, JokesAdapter.ViewHolder>(JokeDiffCallback()) {

    var callback: JokeAdapterCallback? = null

    class ViewHolder(
        private val binding: ListItemJokeBinding,
        private val callback: JokeAdapterCallback?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, item: Joke) {
            binding.joke = item
            binding.buttonRemove.setOnClickListener {
                callback?.onRemove(position)
            }
            binding.buttonShare.setOnClickListener {
                callback?.onShare(item)
            }
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_joke,
                parent, false
            ),
            callback
        )
    }
}

private class JokeDiffCallback : DiffUtil.ItemCallback<Joke>() {

    override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
        return oldItem == newItem
    }
}