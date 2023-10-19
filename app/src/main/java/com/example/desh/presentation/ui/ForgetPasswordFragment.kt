package com.example.desh.presentation.ui

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.desh.R
import com.example.desh.databinding.FragmentForgetPasswordBinding
import java.util.Locale

class ForgetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgetPasswordBinding
    private val args by navArgs<ForgetPasswordFragmentArgs>()
    private var timeLeftInMillis = 0L
    private var countDownTimer: CountDownTimer? = null
    private var timerIsRunning = false
    private var remainingTimeInMillis = 100000L
    private var endTime = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgetPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            /*when (args.verifyType) {
                VerificationType.PHONE -> {
                    txMail.text = resources.getString(R.string.phone)
                    txtDesc.text = resources.getString(R.string.please_check_your_phone_sms)
                    emailTxInputForget.isVisible = false
                    mobileTxInputForget.isVisible = true
                }

                VerificationType.EMAIL -> {
                    txMail.text = resources.getString(R.string.mail)
                    txtDesc.text = resources.getString(R.string.please_check_your_mail_inbox)
                    emailTxInputForget.isVisible = true
                    mobileTxInputForget.isVisible = false
                }
            }
*/
           /* btnSendCode.setOnClickListener {
                startOrStopTimer()
            }*/

            btnConfirm.setOnClickListener {
                val action = ForgetPasswordFragmentDirections.actionForgetPasswordFragmentToConfirmNewPassword()
                findNavController().navigate(action)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        stopOrResetTimer()
    }

    private fun stopOrResetTimer() {
        countDownTimer?.cancel()
        timerIsRunning = false
        timeLeftInMillis = 0L
        remainingTimeInMillis = 100000L
    }


    private fun startOrStopTimer() {
        if (!timerIsRunning) {
            countDownTimer?.cancel()
            countDownTimer = object : CountDownTimer(remainingTimeInMillis, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    remainingTimeInMillis = millisUntilFinished
                    timeLeftInMillis = millisUntilFinished
                    timerIsRunning = true
                    binding.btnSendCode.isEnabled = false
                    disableTryAgain(false)
                    binding.txtTimerCountDown.text =
                        "${resources.getString(R.string.try_again) + millisUntilFinished.convertToTimeFormat()}"
                }

                override fun onFinish() {
                    timerIsRunning = false
                    remainingTimeInMillis = 100000L
                    timeLeftInMillis = 0L
                    countDownTimer?.cancel()
                    disableTryAgain(true)
                }
            }.start()
        } else {
            countDownTimer?.cancel()
            timerIsRunning = false
            disableTryAgain(true)
            // binding.startOrStopBtn.text = "Start"
        }
    }

    fun disableTryAgain(enabled: Boolean) {
        binding.btnSendCode.isEnabled = enabled
        binding.txtTimerCountDown.isVisible = !enabled
        binding.txtDesc.isVisible = !enabled
        if (!enabled) {
            binding.btnSendCode.alpha = 0.4f
        } else {
            binding.btnSendCode.alpha = 0.9f
        }
    }

    private fun Long.convertToTimeFormat(): String {
        val minutes = (this / 1000).toInt() / 60
        val seconds = (this / 1000).toInt() % 60

        return java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }

}