package momonyan.weathergeta

public enum class WeatherEnum(val id: Int, val eng: String, val kanzi: String, val hiragana: String) {
    ClearSky(0, "Clear sky", "快晴", "かいせい"),
    FewClouds(1, "Few clouds", "晴れ", "はれ"),
    ScatteredClouds(2, "Scattered clouds", "曇り", "くもり"),
    BrokenClouds(3, "Broken clouds", "ちぎれ雲", "ちぎれぐも"),
    ShowerRain(4, "Shower rain", "にわか雨", "にわかあめ"),
    Rain(5, "Rain", "雨", "あめ"),
    ThunderStorm(6, "Thunderstorm", "雷", "かみなり"),
    Snow(7, "Snow", "雪", "ゆき"),
    Mist(8, "Mist", "霧", "きり")

}