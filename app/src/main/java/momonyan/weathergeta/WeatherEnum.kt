package momonyan.weathergeta

public enum class WeatherEnum(val id: Int, val eng: String, val kanzi: String, val hiragana: String) {
    ClearSky(0, "clear sky", "快晴", "かいせい"),
    FewClouds(1, "few clouds", "晴れ", "はれ"),
    ScatteredClouds(2, "scattered clouds", "曇り", "くもり"),
    BrokenClouds(3, "broken clouds", "ちぎれ雲", "ちぎれぐも"),
    ShowerRain(4, "shower rain", "にわか雨", "にわかあめ"),
    Rain(5, "rain", "雨", "あめ"),
    ThunderStorm(6, "thunderstorm", "雷", "かみなり"),
    Snow(7, "snow", "雪", "ゆき"),
    Mist(8, "mist", "霧", "きり")

}