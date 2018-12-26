package momonyan.weathergeta

import java.util.*

class RandomWeather {
    fun getWeatherKanzi(): String {
        val random = Random().nextInt(20)
        when (random) {
            0, 1, 2, 3, 4, 5 -> return WeatherEnum.Clear.kanzi
            6, 7, 8, 9 -> return WeatherEnum.Clouds.kanzi
            10, 11, 12, 13 -> return WeatherEnum.Rain.kanzi
            14, 15, 16 -> return WeatherEnum.ThunderStorm.kanzi
            17, 18 -> return WeatherEnum.Snow.kanzi
            19 -> return WeatherEnum.Mist.kanzi
            else -> return WeatherEnum.Clear.kanzi
        }
    }
    fun getWeatherHiragana(): String {
        val random = Random().nextInt(20)
        when (random) {
            0, 1, 2, 3, 4, 5 -> return WeatherEnum.Clear.hiragana
            6, 7, 8, 9 -> return WeatherEnum.Clouds.hiragana
            10, 11, 12, 13 -> return WeatherEnum.Rain.hiragana
            14, 15, 16 -> return WeatherEnum.ThunderStorm.hiragana
            17, 18 -> return WeatherEnum.Snow.hiragana
            19 -> return WeatherEnum.Mist.hiragana
            else -> return WeatherEnum.Clear.hiragana
        }
    }
}