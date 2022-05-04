package com.example.firebasemessaging

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FireBaseService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("message", "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("message", "Message data payload: ${remoteMessage.data}")
            sendNotification(remoteMessage.data.toString())
            val data = remoteMessage.data
            val nom = data["nom"]
            val prenom = data["prenom"]
            val age = data["age"]
            Log.d("resultat", nom + " " + prenom + " " + age)
            Log.d("RESULTAT", "Message received from: " + remoteMessage.data.size)
            for (key in remoteMessage.data.keys) {
                Log.d("resultat", key + " data: " + remoteMessage.data[key])
            }
        }


        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d("message", "Message Notification Body: ${it.body}")
            sendNotification(it.body)
        }
    }

    //On recupere le message
    private fun sendNotification(body: String?) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(
                this@FireBaseService.applicationContext,
                "$body", Toast.LENGTH_SHORT
            ).show()
        }
    }
}