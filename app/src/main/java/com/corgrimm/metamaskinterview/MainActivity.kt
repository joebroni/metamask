package com.corgrimm.metamaskinterview

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var webView : WebView
    private lateinit var address : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        webView = findViewById(R.id.webview)
        address = findViewById(R.id.address)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.metamask.io")
        webView.webChromeClient = object : WebChromeClient() {}
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
//                if (view != null) {
//                    view.evaluateJavascript(
//                        "javascript:alert('Hello, World!');", null
//                    )
//                }
            }
        }

        address.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_GO) {
                val formattedUrl = formatUrlEntry(textView.text.toString())
                address.setText(formattedUrl)
                webView.loadUrl(formattedUrl)
                true
            }
            else {
                false
            }
        }

    }

    fun formatUrlEntry(url : String) : String {
        var newUrl = url
        if (!url.contains("http")) {
            newUrl = "https://" + url
        }

        return newUrl.lowercase()
    }
}