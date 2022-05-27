package com.github.libretube

import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Dialog with different options for a selected video.
 *
 * Needs the [videoId] to load the content from the right video.
 */
class VideoOptionsDialog(private val videoId: String) : DialogFragment() {
    /**
     * List that stores the different menu options. In the future could be add more options here.
     */
    private val list = listOf("Play on background")

    /**
     * Dialog that returns a [MaterialAlertDialogBuilder] showing a menu of options.
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.video_options_dialog_item,
                    list
                )
            ) { dialog, which ->
                // For now, this checks the position of the option with the position that is in the
                // list. I don't like it, but we will do like this for now.
                when (which) {
                    // This for example will be the "Background mode" option
                    0 -> {
                        BackgroundMode.getInstance().playOnBackgroundMode(requireContext(), videoId)
                    }
                    else -> {
                        dialog.dismiss()
                    }
                }
            }
            .show()
    }

    companion object {
        const val TAG = "VideoOptionsDialog"
    }
}