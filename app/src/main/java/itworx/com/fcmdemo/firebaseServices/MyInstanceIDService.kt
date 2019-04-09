package itworx.com.fcmdemo.firebaseServices

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import android.content.Intent
import itworx.com.fcmdemo.azureServices.RegistrationIntentService


class MyInstanceIDService: FirebaseInstanceIdService() {

    val TAG = "PushNotifService"
    lateinit var name: String

    override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Token : ${token}")

        Log.d(TAG, "Refreshing FCM Registration Token")

        val intent = Intent(this, RegistrationIntentService::class.java)
        startService(intent)
    }

}