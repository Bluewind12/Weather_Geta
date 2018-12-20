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


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensorX: Float = 0.0f
    private var sensorY: Float = 0.0f
    private var sensorZ: Float = 0.0f
    private var sensorFlag = false
    private var flag = false
    private var useLanguageFlag = 0 //0:漢字 1:ひらがな
    private val kanzi = arrayListOf<String>("晴れ", "曇り", "雨", "雷")
    private val hiragana = arrayListOf<String>("はれ", "くもり", "あめ", "かみなり")

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


        if (sensorY <= -4.5f && !sensorFlag) {
            sensorFlag = true
            textView2.text = "起こして！"
        }
        if (sensorY >= 8.0f && sensorFlag) {
            sensorFlag = false
            if (flag) {
                when (Random().nextInt(10)) {
                    0, 1, 2, 3, 4 -> textView2.text = viewLangageSet(0)
                    5, 6 -> textView2.text = viewLangageSet(1)
                    7, 8, 9 -> {
                        textView2.text = viewLangageSet(2)
                        flag = false
                    }
                }
            } else {
                when (Random().nextInt(10)) {
                    0, 1, 2 -> textView2.text = viewLangageSet(0)
                    3, 4, 5, 6 -> textView2.text = viewLangageSet(1)
                    7, 8, 9 -> textView2.text = viewLangageSet(3)
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

    private fun viewLangageSet(weatherNum: Int): String {
        when (useLanguageFlag) {
            0 -> return kanzi[weatherNum]
            1 -> return hiragana[weatherNum]
            else -> error("エラーコード1")
        }
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
