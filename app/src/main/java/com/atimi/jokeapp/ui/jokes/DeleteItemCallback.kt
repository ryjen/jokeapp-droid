package com.atimi.jokeapp.ui.jokes

import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.atimi.jokeapp.R
import com.google.android.material.snackbar.Snackbar

class DeleteItemCallback(
    private val parent: View,
    private val viewModel: JokesViewModel
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        val position = viewHolder.bindingAdapterPosition

        val joke = viewModel.removeJoke(position) ?: return

        // below line is to display our snackbar with action.
        Snackbar.make(
            parent,
            R.string.joke_removed,
            Snackbar.LENGTH_LONG
        ).setAction(
            R.string.label_undo
        ) {
            viewModel.undoRemovedJoke(position, joke)
        }.show()
    }

}
