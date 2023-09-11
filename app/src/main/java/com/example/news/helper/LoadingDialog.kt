package com.example.news.helper

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.example.news.R
import com.example.news.databinding.LoadingDialogBinding

class LoadingDialog (private val context: Context) {
    private val dialog = Dialog(context)
    private val handler = Handler()
    private val dismissRunnable = Runnable {
        dismiss()
    }

    init {
        // inflate and binding the layout
        val dialogView = View.inflate(context, R.layout.loading_dialog, null)
        val binding = LoadingDialogBinding.bind(dialogView)

        // Set the window background to be transparent
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // set to inflate layout
        dialog.setContentView(binding.root)
        // set to be non-cancelable
        dialog.setCancelable(false)
        //to adjust the appearance
        dialog.window?.apply {
            setGravity(Gravity.CENTER)
            attributes = attributes.apply {
                flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
                dimAmount = 0.7f
            }
        }
    }

    // display the dialog
    fun show() {
        dialog.show()
        // Schedule a dismissal after 3 seconds (3000 milliseconds)
        handler.postDelayed(dismissRunnable, 3000)
    }

    // dismiss the dialog
    fun dismiss() {
        handler.removeCallbacks(dismissRunnable) // Remove the callback if dismissed manually
        dialog.dismiss()
    }
}