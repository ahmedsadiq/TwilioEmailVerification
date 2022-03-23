package com.android.emailverification.twilioemailauthentication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.emailverification.twilioemailauthentication.Utils.AppConstants
import com.android.emailverification.twilioemailauthentication.R
import com.android.emailverification.twilioemailauthentication.databinding.ActivityOtpBinding

/**
 * Created by Ahmed Sadiq
 */
class OTPActivity : AppCompatActivity() {
    private lateinit var codeInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityCodeVerificationBinding = DataBindingUtil.setContentView<ActivityOtpBinding>(this,
            R.layout.activity_otp
        )
        codeInput = activityCodeVerificationBinding.code
    }

    fun verify(v: View) {
        val code = codeInput.text.toString()
        val serverCode:String = intent.getStringExtra(AppConstants.CODE).toString()
        if(serverCode == code)
            Toast.makeText(applicationContext, getString(R.string.verified), Toast.LENGTH_LONG).show()
        else
            Toast.makeText(applicationContext, getString(R.string.not_verified), Toast.LENGTH_LONG).show()
    }
}