package com.example.firebasemessaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get token delivered from FireBase
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    println("La recuperation du token a echoue")
                    return@OnCompleteListener
                }

                // On recupere le TOKEN
                val token = task.result

                // On recupére et on affiche le token
                Log.d("TOKEN",token)
            })
    }

    fun onClickSubcribe(view: View) {
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = "abonné"
                if (!task.isSuccessful) {
                    msg = "echec"
                }
                Log.d("abonner", msg)

            }
    }
    fun onClickUnsubcribe(view: View) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic("weather")
    }
}