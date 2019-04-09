package itworx.com.fcmdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import itworx.com.fcmdemo.azureServices.RegistrationIntentService
import android.content.Intent



class MainActivity : AppCompatActivity() {

    val TAG = "PushNotifService"
    private var regToken: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        regToken = findViewById(R.id.reg_token)
//        getRegistrationToken()
        val intent = Intent(this, RegistrationIntentService::class.java)
        startService(intent)
    }

    private fun getRegistrationToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    regToken?.text="getInstanceId failed"
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                regToken?.text = token
            })
    }
}
