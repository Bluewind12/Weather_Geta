package momonyan.weathergeta

import android.content.DialogInterface
import android.content.Intent
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.hardware.Sensor
import android.os.Bundle
import android.hardware.SensorEventListener
import android.net.Uri
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
import java.util.*
import android.util.Log
import android.widget.TextView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Thread.currentThread
import org.json.JSONException
import org.json.JSONArray
import android.R.attr.data


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensorX: Float = 0.0f
    private var sensorY: Float = 0.0f
    private var sensorZ: Float = 0.0f
    private var sensorFlag = false
    private var flag = false

    private var nums = 0
    private var position = ""
    private var weather = ""

    private val landStructure = LandSeting()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        button.setOnClickListener {
            getWeather()
        }
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


        if (sensorY <= -4.5f && !sensorFlag) {
            sensorFlag = true
            textView2.text = "起こして！"
        }
        if (sensorY >= 8.0f && sensorFlag) {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> {
                //TODO 説明画面への移行
                Toast.makeText(this, "説明", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menu2 -> {
                //TODO 設定画面への移行
                Toast.makeText(this, "設定", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menu3 -> {
                //TODO プライバシーポリシーへの移行
                AlertDialog.Builder(this)
                    .setTitle("Webページを開きます")
                    .setMessage(getString(R.string.privacy_description))
                    .setPositiveButton("はい") { dialog, which ->
                        val privacyURL = Uri.parse(getString(R.string.privacy_url))
                        val intent = Intent(Intent.ACTION_VIEW, privacyURL)
                        startActivity(intent)
                    }
                    .setNegativeButton("キャンセル", null)
                    .show()
                return true
            }
        }
        return true
    }

    private fun getWeather() {
        nums++
        val randomInts = Random().nextInt(48)
        //ランダムに取得
        landStructure.getLatLon(randomInts)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.openweathermap.org/data/2.5/find?lat=" + landStructure.lat + "&lon=" + landStructure.lon + "&cnt=1&appid=3df51d5c17d48c9751598d7474ce0bbe")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                val data = response.body()?.string()
                Log.d("AAA:LOGDATA : ", data)

                try {
                    val rootObj = JSONObject(data)
                    val listArray = rootObj.getJSONArray("list")

                    val obj = listArray.getJSONObject(0)

                    // 地点ID
                    val id = obj.getInt("id")

                    // 地点名
                    val cityName = obj.getString("name")

                    // 気温(Kから℃に変換)
                    val mainObj = obj.getJSONObject("main")
                    val currentTemp = (mainObj.getDouble("temp") - 273.15f).toFloat()

                    val minTemp = (mainObj.getDouble("temp_min") - 273.15f).toFloat()

                    val maxTemp = (mainObj.getDouble("temp_max") - 273.15f).toFloat()

                    // 湿度
                    if (mainObj.has("humidity")) {
                        val humidity = mainObj.getInt("humidity")
                    }

                    // 取得時間
                    val time = obj.getLong("dt")

                    // 天気
                    val weatherArray = obj.getJSONArray("weather")
                    val weatherObj = weatherArray.getJSONObject(0)
                    val iconId = weatherObj.getString("icon")
                    val weather = weatherObj.getString("main")

                    Log.d("AAA:地点", "回数：" + nums + ":" + cityName)
                    Log.d("AAA:設定地点","回数："+nums +"："+landStructure.city)
                    Log.d("AAA:天気:", "回数：" + nums + ":" + weather)

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })

    }
}
