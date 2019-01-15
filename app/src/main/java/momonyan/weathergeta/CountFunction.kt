package momonyan.weathergeta

import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import java.util.*

class CountFunction(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
    private lateinit var city: String
    private lateinit var weather: String
    private lateinit var textView: TextView
    private lateinit var activity: MainActivity
    private var i = 0

    override fun onFinish() {
        textView.text = "場所：$city\n天気：$weather"
        activity.downFlag = false
    }

    // インターバルで呼ばれる
    override fun onTick(millisUntilFinished: Long) {
        if(i == 0) {
            textView.text = "カラン"
            i++
        }else{
            textView.text = "コロン"
            i--
        }
        Log.d("Tag",1.toString())
    }

    fun setData(city: String, weather: String, textView: TextView,activity: MainActivity) {
        this.city = city
        this.weather = weather
        this.textView = textView
        this.activity = activity
    }
}