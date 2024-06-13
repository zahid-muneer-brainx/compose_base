package com.brainxtech.extensions.utils

import android.app.Activity
import android.content.Context
import com.google.android.play.core.review.ReviewManagerFactory

class InAppReviewManager(private val context: Context) {
    private val reviewManager by lazy {  ReviewManagerFactory.create(context) }

    fun startReviewFlow(activity: Activity, onReviewFlowFinished: (Boolean) -> Unit) {
        reviewManager.requestReviewFlow().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                reviewManager.launchReviewFlow(activity, task.result).addOnCompleteListener {

                }
            } else {
                // There was some problem, log or handle the error code.
                onReviewFlowFinished(false)
            }
        }
    }
}