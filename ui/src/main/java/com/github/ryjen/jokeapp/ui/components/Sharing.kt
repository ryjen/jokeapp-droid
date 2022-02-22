package com.github.ryjen.jokeapp.ui.components

import android.content.Context
import android.content.Intent
import com.github.ryjen.jokeapp.ui.R

object Share {

    fun text(context: Context, content: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, content)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, context.getString(R.string.action_share))
        context.startActivity(shareIntent)
    }
}
