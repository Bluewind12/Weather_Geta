package momonyan.weather_geta

import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.hardware.Sensor
import android.os.Bundle
import android.hardware.SensorEventListener
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensorX: Float = 0.0f
    private var sensorY: Float = 0.0f
    private var sensorZ: Float = 0.0f
    private var sensorFlag = false
    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        // Listenerの登録
        val accel = sensorManager.getDefaultSensor(
            Sensor.TYPE_ACCELEROMETER
        )

        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL)
    }

    // 解除
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            sensorX = event.values[0]
            sensorY = event.values[1]
            sensorZ = event.values[2]

            val strTmp = ("加速度センサー\n"
                    + " X: " + sensorX + "\n"
                    + " Y: " + sensorY + "\n"
                    + " Z: " + sensorZ)
            textView.text = strTmp

            showInfo(event)
        }


        if (sensorY <= 0.0f && !sensorFlag) {
            sensorFlag = true
            textView2.text = "起こして！"
        }
        if (sensorY >= 9.0f && sensorFlag) {
            sensorFlag = false
            if (flag) {
                when (Random().nextInt(10)) {
                    0 -> textView2.text = getString(R.string.sunny_kan)
                    1 -> textView2.text = getString(R.string.sunny_kan)
                    2 -> textView2.text = getString(R.string.sunny_kan)
                    3 -> textView2.text = getString(R.string.sunny_kan)
                    4 -> textView2.text = getString(R.string.sunny_kan)
                    5 -> textView2.text = getString(R.string.cloud_kan)
                    6 -> textView2.text = getString(R.string.cloud_kan)
                    7 -> {
                        textView2.text = getString(R.string.lain_kan)
                        flag = false
                    }
                    8 -> {
                        textView2.text = getString(R.string.lain_kan)
                        flag = false
                    }
                    9 -> {
                        textView2.text = getString(R.string.lain_kan)
                        flag = false
                    }
                }
            } else {
                when (Random().nextInt(10)) {
                    0 -> textView2.text = getString(R.string.sunny_kan)
                    1 -> textView2.text = getString(R.string.sunny_kan)
                    2 -> textView2.text = getString(R.string.sunny_kan)
                    3 -> textView2.text = getString(R.string.cloud_kan)
                    4 -> textView2.text = getString(R.string.cloud_kan)
                    5 -> textView2.text = getString(R.string.cloud_kan)
                    6 -> textView2.text = getString(R.string.cloud_kan)
                    7 -> textView2.text = getString(R.string.thunder_kan)
                    8 -> textView2.text = getString(R.string.thunder_kan)
                    9 -> textView2.text = getString(R.string.thunder_kan)
                }
                flag = true
            }
        }
    }

    private fun showInfo(event: SensorEvent) {

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }
}
