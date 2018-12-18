package momonyan.weathergeta

import android.hardware.SensorEvent
import android.hardware.Sensor.TYPE_ACCELEROMETER
import android.hardware.SensorManager
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.support.v4.content.ContextCompat.getSystemService
import android.os.Bundle
import android.widget.TextView
import android.hardware.SensorEventListener
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class AccelerChacker : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

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
        val sensorX: Float
        val sensorY: Float
        val sensorZ: Float

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
    }

    private fun showInfo(event: SensorEvent) {

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }
}
