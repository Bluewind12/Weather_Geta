package momonyan.weathergeta

class LandSeting {

    var lat: Double = 0.0
    var lon: Double = 0.0
    var city: String = "nothing"
    //県名から座標取得
    fun getLatLon(case: Int, setLangage: Int) {
        when (setLangage) {
            //漢字
            0 -> {
                when (case) {
                    0 -> {
                        city = "青森県"
                        lat = 40.822117
                        lon = 140.747376
                    }
                    1 -> {
                        city = "愛知県"
                        lat = 35.18028
                        lon = 136.90667
                    }
                    2 -> {
                        city = "愛媛県"
                        lat = 33.84167
                        lon = 132.76611
                    }
                    3 -> {
                        city = "茨城県"
                        lat = 36.34139
                        lon = 140.44667
                    }
                    4 -> {
                        city = "岡山県"
                        lat = 34.66167
                        lon = 133.935
                    }
                    5 -> {
                        city = "沖縄県"
                        lat = 26.2125
                        lon = 127.68111
                    }
                    6 -> {
                        city = "岩手県"
                        lat = 39.70361
                        lon = 141.1525
                    }
                    7 -> {
                        city = "岐阜県"
                        lat = 35.39111
                        lon = 136.72222
                    }
                    8 -> {
                        city = "宮崎県"
                        lat = 31.91111
                        lon = 131.42389
                    }
                    9 -> {
                        city = "宮城県"
                        lat = 38.26889
                        lon = 140.87194
                    }
                    10 -> {
                        city = "京都府"
                        lat = 35.02139
                        lon = 135.75556
                    }
                    11 -> {
                        city = "熊本県"
                        lat = 32.78972
                        lon = 130.74167
                    }
                    12 -> {
                        city = "群馬県"
                        lat = 36.39111
                        lon = 139.06083
                    }
                    13 -> {
                        city = "広島県"
                        lat = 34.39639
                        lon = 132.45944
                    }
                    14 -> {
                        city = "香川県"
                        lat = 34.34028
                        lon = 134.04333
                    }
                    15 -> {
                        city = "高知県"
                        lat = 33.55972
                        lon = 133.53111
                    }
                    16 -> {
                        city = "佐賀県"
                        lat = 33.24944
                        lon = 130.29889
                    }
                    17 -> {
                        city = "埼玉県"
                        lat = 35.85694
                        lon = 139.64889
                    }
                    18 -> {
                        city = "三重県"
                        lat = 34.73028
                        lon = 136.50861
                    }
                    19 -> {
                        city = "山形県"
                        lat = 38.24056
                        lon = 140.36333
                    }
                    20 -> {
                        city = "山口県"
                        lat = 34.18583
                        lon = 131.47139
                    }
                    21 -> {
                        city = "山梨県"
                        lat = 35.66389
                        lon = 138.56833
                    }
                    22 -> {
                        city = "滋賀県"
                        lat = 35.00444
                        lon = 135.86833
                    }
                    23 -> {
                        city = "鹿児島県"
                        lat = 31.56028
                        lon = 130.55806
                    }
                    24 -> {
                        city = "秋田県"
                        lat = 39.71861
                        lon = 140.1025
                    }
                    25 -> {
                        city = "新潟県"
                        lat = 37.90222
                        lon = 139.02361
                    }
                    26 -> {
                        city = "神奈川県"
                        lat = 35.44778
                        lon = 139.6425
                    }
                    27 -> {
                        city = "青森県"
                        lat = 40.82444
                        lon = 140.74
                    }
                    28 -> {
                        city = "静岡県"
                        lat = 34.97694
                        lon = 138.38306
                    }
                    29 -> {
                        city = "石川県"
                        lat = 36.59444
                        lon = 136.62556
                    }
                    30 -> {
                        city = "千葉県"
                        lat = 35.60472
                        lon = 140.12333
                    }
                    31 -> {
                        city = "大阪府"
                        lat = 34.68639
                        lon = 135.52
                    }
                    32 -> {
                        city = "大分県"
                        lat = 33.23806
                        lon = 131.6125
                    }
                    33 -> {
                        city = "長崎県"
                        lat = 32.74472
                        lon = 129.87361
                    }
                    34 -> {
                        city = "長野県"
                        lat = 36.65139
                        lon = 138.18111
                    }
                    35 -> {
                        city = "鳥取県"
                        lat = 35.50361
                        lon = 134.23833
                    }
                    36 -> {
                        city = "島根県"
                        lat = 35.47222
                        lon = 133.05056
                    }
                    37 -> {
                        city = "東京都"
                        lat = 35.68944
                        lon = 139.69167
                    }
                    38 -> {
                        city = "徳島県"
                        lat = 34.06583
                        lon = 134.55944
                    }
                    39 -> {
                        city = "栃木県"
                        lat = 36.56583
                        lon = 139.88361
                    }
                    40 -> {
                        city = "奈良県"
                        lat = 34.68528
                        lon = 135.83278
                    }
                    41 -> {
                        city = "富山県"
                        lat = 36.69528
                        lon = 137.21139
                    }
                    42 -> {
                        city = "福井県"
                        lat = 36.06528
                        lon = 136.22194
                    }
                    43 -> {
                        city = "福岡県"
                        lat = 33.60639
                        lon = 130.41806
                    }
                    44 -> {
                        city = "福島県"
                        lat = 37.75
                        lon = 140.46778
                    }
                    45 -> {
                        city = "兵庫県"
                        lat = 34.69139
                        lon = 135.18306
                    }
                    46 -> {
                        city = "北海道"
                        lat = 43.06417
                        lon = 141.34694
                    }
                    47 -> {
                        city = "和歌山県"
                        lat = 34.22611
                        lon = 135.1675
                    }
                }
            }
            //ひらがな
            1 -> {
                when (case) {
                    0 -> {
                        city = "あおもりけん"
                        lat = 40.822117
                        lon = 140.747376
                    }
                    1 -> {
                        city = "あいちけん"
                        lat = 35.18028
                        lon = 136.90667
                    }
                    2 -> {
                        city = "えひめけん"
                        lat = 33.84167
                        lon = 132.76611
                    }
                    3 -> {
                        city = "いばらぎけん"
                        lat = 36.34139
                        lon = 140.44667
                    }
                    4 -> {
                        city = "おかやまけん"
                        lat = 34.66167
                        lon = 133.935
                    }
                    5 -> {
                        city = "おきなわけん"
                        lat = 26.2125
                        lon = 127.68111
                    }
                    6 -> {
                        city = "いわてけん"
                        lat = 39.70361
                        lon = 141.1525
                    }
                    7 -> {
                        city = "ぎふけん"
                        lat = 35.39111
                        lon = 136.72222
                    }
                    8 -> {
                        city = "みやざきけん"
                        lat = 31.91111
                        lon = 131.42389
                    }
                    9 -> {
                        city = "みやぎけん"
                        lat = 38.26889
                        lon = 140.87194
                    }
                    10 -> {
                        city = "きょうとふ"
                        lat = 35.02139
                        lon = 135.75556
                    }
                    11 -> {
                        city = "とちぎけん"
                        lat = 32.78972
                        lon = 130.74167
                    }
                    12 -> {
                        city = "ぐんまけん"
                        lat = 36.39111
                        lon = 139.06083
                    }
                    13 -> {
                        city = "ひろしまけん"
                        lat = 34.39639
                        lon = 132.45944
                    }
                    14 -> {
                        city = "かがわけん"
                        lat = 34.34028
                        lon = 134.04333
                    }
                    15 -> {
                        city = "こうちけん"
                        lat = 33.55972
                        lon = 133.53111
                    }
                    16 -> {
                        city = "さがけん"
                        lat = 33.24944
                        lon = 130.29889
                    }
                    17 -> {
                        city = "さいたまけん"
                        lat = 35.85694
                        lon = 139.64889
                    }
                    18 -> {
                        city = "みえけん"
                        lat = 34.73028
                        lon = 136.50861
                    }
                    19 -> {
                        city = "やまがたけん"
                        lat = 38.24056
                        lon = 140.36333
                    }
                    20 -> {
                        city = "やまぐちけん"
                        lat = 34.18583
                        lon = 131.47139
                    }
                    21 -> {
                        city = "やまなしけん"
                        lat = 35.66389
                        lon = 138.56833
                    }
                    22 -> {
                        city = "しがけん"
                        lat = 35.00444
                        lon = 135.86833
                    }
                    23 -> {
                        city = "かごしまけん"
                        lat = 31.56028
                        lon = 130.55806
                    }
                    24 -> {
                        city = "あきたけん"
                        lat = 39.71861
                        lon = 140.1025
                    }
                    25 -> {
                        city = "にいがたけん"
                        lat = 37.90222
                        lon = 139.02361
                    }
                    26 -> {
                        city = "かながわけん"
                        lat = 35.44778
                        lon = 139.6425
                    }
                    27 -> {
                        city = "あおもりけん"
                        lat = 40.82444
                        lon = 140.74
                    }
                    28 -> {
                        city = "しずおかけん"
                        lat = 34.97694
                        lon = 138.38306
                    }
                    29 -> {
                        city = "いしかわけん"
                        lat = 36.59444
                        lon = 136.62556
                    }
                    30 -> {
                        city = "ちばけん"
                        lat = 35.60472
                        lon = 140.12333
                    }
                    31 -> {
                        city = "おおさかけん"
                        lat = 34.68639
                        lon = 135.52
                    }
                    32 -> {
                        city = "おおいたけん"
                        lat = 33.23806
                        lon = 131.6125
                    }
                    33 -> {
                        city = "ながさきけん"
                        lat = 32.74472
                        lon = 129.87361
                    }
                    34 -> {
                        city = "ながのけん"
                        lat = 36.65139
                        lon = 138.18111
                    }
                    35 -> {
                        city = "とっとりけん"
                        lat = 35.50361
                        lon = 134.23833
                    }
                    36 -> {
                        city = "しまねけん"
                        lat = 35.47222
                        lon = 133.05056
                    }
                    37 -> {
                        city = "とうきょうと"
                        lat = 35.68944
                        lon = 139.69167
                    }
                    38 -> {
                        city = "とくしまけん"
                        lat = 34.06583
                        lon = 134.55944
                    }
                    39 -> {
                        city = "とちぎけん"
                        lat = 36.56583
                        lon = 139.88361
                    }
                    40 -> {
                        city = "ならけん"
                        lat = 34.68528
                        lon = 135.83278
                    }
                    41 -> {
                        city = "とやまけん"
                        lat = 36.69528
                        lon = 137.21139
                    }
                    42 -> {
                        city = "ふくいけん"
                        lat = 36.06528
                        lon = 136.22194
                    }
                    43 -> {
                        city = "ふくおかけん"
                        lat = 33.60639
                        lon = 130.41806
                    }
                    44 -> {
                        city = "ふくしまけん"
                        lat = 37.75
                        lon = 140.46778
                    }
                    45 -> {
                        city = "ひょうごけん"
                        lat = 34.69139
                        lon = 135.18306
                    }
                    46 -> {
                        city = "ほっかいどう"
                        lat = 43.06417
                        lon = 141.34694
                    }
                    47 -> {
                        city = "わかやまけん"
                        lat = 34.22611
                        lon = 135.1675
                    }
                }
            }
        }

    }
}