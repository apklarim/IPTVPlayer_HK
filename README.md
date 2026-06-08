# IPTV Player HK 📺

Türkçe IPTV Player uygulaması - M3U playlist destekli, ExoPlayer tabanlı video oynatıcı.

## 🎯 Özellikler

- ✅ M3U/M3U8 playlist desteği
- ✅ Kanal logolarının gösterilmesi
- ✅ Favoriler sistemi
- ✅ ExoPlayer ile HD video oynatma
- ✅ Açık/Koyu tema desteği
- ✅ Oynatma geçmişi
- ✅ Türkçe arayüz

## 📋 Gereksinimler

- Android 5.0+ (API 21+)
- Kotlin 1.9+
- Gradle 8.0+

## 🛠️ Teknolojiler

- **Android Native**: Kotlin
- **Video Player**: ExoPlayer (Media3)
- **Database**: Room
- **Networking**: Retrofit + OkHttp
- **Image Loading**: Glide
- **Asynchronous**: Coroutines

## 📁 Proje Yapısı

```
IPTVPlayer_HK/
├── app/
│   └── src/main/
│       ├── java/com/iptv/player/
│       │   ├── data/
│       │   │   ├── model/        # Veri modelleri
│       │   │   ├── parser/       # M3U Parser
│       │   │   └── repository/   # Database & Repo
│       │   └── ui/
│       │       ├── activities/   # MainActivity, VideoPlayerActivity
│       │       ├── fragments/    # ChannelsFragment, FavoritesFragment
│       │       ├── adapters/     # RecyclerView Adapters
│       │       └── viewmodels/   # ViewModel'ler
│       └── res/
│           ├── layout/          # XML layoutları
│           ├── menu/            # Navigation Menu
│           └── values/          # Strings, Colors, Styles
└── build.gradle.kts
```

## 🚀 Hızlı Başlangıç

### 1. Playlist Ekle
Uygulamada "Oynatma Listesi Ekle" butonuna tıklayarak M3U dosyası URL'sini girin.

### 2. Kanalları Oynat
Kanal listesinden bir kanal seçerek oynatmaya başlayın.

### 3. Favorilere Ekle
Kanalı favorilere ekleyerek hızlı erişim sağlayın.

## 📦 Bağımlılıklar

```kotlin
// AndroidX
androidx.core:core-ktx:1.12.0
androidx.appcompat:appcompat:1.6.1
androidx.constraintlayout:constraintlayout:2.1.4

// Media3 (ExoPlayer)
androidx.media3:media3-exoplayer:1.1.1
androidx.media3:media3-ui:1.1.1

// Networking
com.squareup.retrofit2:retrofit:2.9.0
com.squareup.okhttp3:okhttp:4.11.0

// Database
androidx.room:room-runtime:2.6.1

// Image Loading
com.github.bumptech.glide:glide:4.15.1

// Coroutines
org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3
```

## 🔧 Kurulum

1. Repository'yi klonlayın:
```bash
git clone https://github.com/apklarim/IPTVPlayer_HK.git
```

2. Android Studio'da projeyi açın

3. Gradle senkronizasyonunu yapın

4. Uygulamayı derle ve çalıştır

## 📱 Desteklenen Format

- **M3U/M3U8 Playlists**: Standart IPTV format
- **Video Codec**: H.264, H.265, VP9
- **Streaming**: HTTP/HTTPS

## 🎬 M3U Playlist Format Örneği

```
#EXTM3U
#EXTINF:-1 tvg-id="1" tvg-name="Kanal Adı" tvg-logo="logo_url" group-title="Kategori",Kanal Adı
http://stream_url.m3u8
```

## 🛡️ İzinler

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

## 📝 Lisans

Bu proje MIT Lisansı altında yayınlanmıştır.

## 👨‍💻 Geliştirici

**Apklarim** - [GitHub](https://github.com/apklarim)

## 🤝 Katkıda Bulunma

PR'ler kabul edilir. Büyük değişiklikler için lütfen önce bir issue açın.

## 📞 İletişim

Sorularınız ve önerileriniz için GitHub Issues'i kullanabilirsiniz.

---

⭐ Eğer bu proje faydalı olduysa, lütfen yıldız verin!
