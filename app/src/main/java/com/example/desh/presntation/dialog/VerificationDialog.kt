package com.example.desh.presntation.dialog

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.desh.R
import com.example.desh.databinding.DialogChooseForgetTypeBinding
import com.example.desh.utils.VerificationType

class VerificationDialog(activity: Activity) : AlertDialog(activity, R.style.AlertDialogTheme) {

    private lateinit var mBinding: DialogChooseForgetTypeBinding
    private var okClickListener: ((VerificationType) -> Unit)? = null

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DialogChooseForgetTypeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setCancelable(true)

        //window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        // window?.requestFeature(Window.FEATURE_NO_TITLE)

        mBinding.btnPhone.setOnClickListener {
            okClickListener?.invoke(VerificationType.PHONE)
        }

        mBinding.btnMail.setOnClickListener {
            okClickListener?.invoke(VerificationType.EMAIL)
        }

    }

    fun setOnClickListener(listener: (VerificationType) -> Unit) {
        okClickListener = listener
    }
}