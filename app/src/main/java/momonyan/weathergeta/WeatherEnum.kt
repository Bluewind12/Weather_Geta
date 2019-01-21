package momonyan.weathergeta

enum class WeatherEnum(val id: Int, val eng: String, val kanzi: String, val hiragana: String) {
    Clear(0, "Clear", "快晴", "かいせい"),
    Clouds(1, "Clouds", "曇り", "くもり"),
    Rain(2, "Rain", "雨", "あめ"),
    ThunderStorm(3, "Thunderstorm", "雷", "かみなり"),
    Snow(4, "Snow", "雪", "ゆき"),
    Mist(5, "Fog", "霧", "きり")

}