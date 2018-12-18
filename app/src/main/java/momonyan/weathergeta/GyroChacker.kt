package momonyan.weathergeta

import android.content.Context
import android.hardware.Sensor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.hardware.SensorEventListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class GyroChacker : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //センサーマネージャの作成
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

    }

    override fun onResume() {
        super.onResume()
        // Listenerの登録
        val gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        if (gyro != null) {
            sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_UI)
        } else {
            val ns = "No Support"
            textView.text = ns
        }
    }

    //解除
    override fun onPause() {
        super.onPause()
        // Listenerを解除
        sensorManager.unregisterListener(this)
    }


    //変化の検出
    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            val sensorX = event.values[0]
            val sensorY = event.values[1]
            val sensorZ = event.values[2]

            val strTmp = String.format(Locale.US, "Gyroscope\n " + " X: %f\n Y: %f\n Z: %f", sensorX, sensorY, sensorZ)
            textView.text = strTmp
            showInfo(event)
        }

    }

    private fun showInfo(event: SensorEvent) {

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }
}