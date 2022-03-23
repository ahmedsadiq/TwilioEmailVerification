package com.android.emailverification.twilioemailauthentication.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.emailverification.twilioemailauthentication.Utils.AppConstants.Companion.CALL_TOKEN
import com.android.emailverification.twilioemailauthentication.Utils.AppConstants.Companion.CODE
import com.android.emailverification.twilioemailauthentication.Utils.AppConstants.Companion.DEVICE_ID
import com.android.emailverification.twilioemailauthentication.Utils.AppConstants.Companion.EMAIL_VERIFIED
import com.android.emailverification.twilioemailauthentication.Utils.AppConstants.Companion.MANUFACTURER
import com.android.emailverification.twilioemailauthentication.Utils.AppConstants.Companion.OPERATING_SYSTEM
import com.android.emailverification.twilioemailauthentication.Utils.AppConstants.Companion.USER_EMAIL
import com.android.emailverification.twilioemailauthentication.Utils.AppConstants.Companion.VERIFICATION_TYPE
import com.android.emailverification.twilioemailauthentication.Utils.AppConstants.Companion.VERSION
import com.android.emailverification.twilioemailauthentication.R
import com.android.emailverification.twilioemailauthentication.CustomViewModel
import com.android.emailverification.twilioemailauthentication.databinding.ActivityHomeBinding
import java.util.*
/**
 * Created by Ahmed Sadiq
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: CustomViewModel
    private lateinit var mView: View
    private lateinit var btnCode: Button
    private lateinit var edEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding() {
        /**
         * View binding required to update layout items
         */
        val activityLoginBinding = DataBindingUtil.setContentView<ActivityHomeBinding>(this,
            R.layout.activity_home
        )
        viewModel = ViewModelProviders.of(this).get(CustomViewModel::class.java)
        activityLoginBinding.varLogin = viewModel
        mView = findViewById(R.id.parent)
        btnCode = findViewById(R.id.button)
        edEmail = findViewById(R.id.email)
        viewModel.init()
        /**
         * Observer added on view model to listen any updates or data changes
         */
        viewModel.responseLiveData?.observe(
            this
        ) { response ->
            if (response != null) {
                if (response.errNum.equals("0")) {
                    /**
                     * Server returning errornum variable with value zero if code is successfully sent to email
                     */
                    Toast.makeText(applicationContext, response.message, Toast.LENGTH_LONG).show()
                    val intent = Intent(this@HomeActivity, OTPActivity::class.java)
                    intent.putExtra(CODE, response.code)
                    startActivity(intent)
                } else
                    Toast.makeText(applicationContext, response.message, Toast.LENGTH_LONG).show()

            }
        }
    }
    /**
     * Params required by server to push code on user's email
     */
    @SuppressLint("HardwareIds")
    fun fetchParams(): HashMap<String, String> {
        val params = HashMap<String, String>()
        params[DEVICE_ID] = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        params[MANUFACTURER] = Build.MANUFACTURER
        params[VERSION] = Build.VERSION.RELEASE
        params[VERIFICATION_TYPE] = "email"
        params[USER_EMAIL] = edEmail.text.toString()
        params[EMAIL_VERIFIED] = "0"
        params[OPERATING_SYSTEM] = "android"
        params[CALL_TOKEN] = "Android"
        return params
    }

    fun sendCode(v: View) {
        viewModel.authenticate(fetchParams())
    }
}