package momonyan.weathergeta

import android.content.Intent
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.hardware.Sensor
import android.os.Bundle
import android.hardware.SensorEventListener
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import org.json.JSONException

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensorX: Float = 0.0f
    private var sensorY: Float = 0.0f
    private var sensorZ: Float = 0.0f
    private var sensorFlag = false
    private var flag = false
    private var useLanguageFlag = 0 //0:漢字 1:ひらがな

    private var nums = 0
    private var position = ""
    private var weather = ""

    private val landStructure = LandSeting()
    private lateinit var outWeather: String


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
            textView2.text = "起こして！"
            getWeather()
        }
        if (sensorY >= 8.0f && sensorFlag) {
            textView2.text = getString(R.string.output_weather, landStructure.city, outWeather)
            sensorFlag = false
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
                settingDialogCreate()
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


                    when (weather) {
                        WeatherEnum.Clear.eng -> outWeather = WeatherEnum.Clear.kanzi
                        WeatherEnum.Clouds.eng -> outWeather = WeatherEnum.Clouds.kanzi
                        WeatherEnum.Rain.eng -> outWeather = WeatherEnum.Rain.kanzi
                        WeatherEnum.ThunderStorm.eng -> outWeather = WeatherEnum.ThunderStorm.kanzi
                        WeatherEnum.Snow.eng -> outWeather = WeatherEnum.Snow.kanzi
                        WeatherEnum.Mist.eng -> outWeather = WeatherEnum.Mist.kanzi
                        else -> outWeather = "???"
                    }

                    sensorFlag = true
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun settingDialogCreate() {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val textView = TextView(this)
        textView.text = "表示は？"
        val radioGroup = RadioGroup(this)
        radioGroup.orientation = RadioGroup.HORIZONTAL
        val radioButton1 = RadioButton(this)
        radioButton1.text = "漢字"
        val radioButton2 = RadioButton(this)
        radioButton2.text = "ひらがな"
        radioGroup.addView(radioButton1)
        radioGroup.addView(radioButton2)
        when (useLanguageFlag) {
            0 -> radioButton1.isChecked = true
            1 -> radioButton2.isChecked = true
        }
        layout.addView(radioGroup)
        //
        val dlg = AlertDialog.Builder(this)
        dlg.setTitle("設定画面")
        dlg.setView(layout)
        dlg.setPositiveButton("決定") { dialog, which ->
            //Yesボタンが押された時の処理
            when {
                radioButton1.isChecked -> useLanguageFlag = 0
                radioButton2.isChecked -> useLanguageFlag = 1
            }
            Toast.makeText(this@MainActivity, "Yesが押されました", Toast.LENGTH_LONG).show()

        }
        dlg.setNegativeButton("キャンセル") { dialog, which ->
            //Noボタンが押された時の処理
            Toast.makeText(this@MainActivity, "Noが押されました", Toast.LENGTH_LONG).show()
        }
        // AlertDialogを表示する
        dlg.show()
    }
}
