package com.example.notificationsproject.ui.theme


import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity

class NewsWebViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra("news_url") ?: ""

        val webView = WebView(this)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        setContentView(webView)

        if (url.isNotEmpty()) {
            webView.loadUrl(url)
        }
    }
}
