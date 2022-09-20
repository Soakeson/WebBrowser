package com.a02308558.webrowser

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val history = History<String>()
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        };

        val webView = WebView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            ).apply {
                weight = 1f
            }
        }
        webView.webViewClient = WebViewClient()

        val addressBar = EditText(this).apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                weight = .9f
            }
        }
        val searchButton = Button(this).apply {
            setText("Search")
            setOnClickListener {
                webView.loadUrl(addressBar.text.toString())
                history.enqueue(addressBar.text.toString())
            }
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                weight = .1f
            }
        }
        val addressBarLayout = LinearLayout(this).apply {
            addView(addressBar)
            addView(searchButton)
        }

        val backButton = Button(this).apply {
            setText("Back")
            setOnClickListener {
                val prev = history.back()
                if (prev != null) {
                    addressBar.setText(prev)
                    webView.loadUrl(prev.toString())
                }
            }
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                weight = .5f
            }
        }

        val forwardButton = Button(this).apply {
            setText("Forward")
            setOnClickListener {
                val next = history.next()
                if (next != null) {
                    addressBar.setText(next)
                    webView.loadUrl(next.toString())
                }
            }
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                weight = .5f
            }
        }

        val navigationBarLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            addView(backButton)
            addView(forwardButton)
        }


        mainLayout.apply {
            addView(addressBarLayout)
            addView(webView)
            addView(navigationBarLayout)
        }
        setContentView(mainLayout)
    }

    fun onSearch(searchTerm: String) {
        println(searchTerm)
    }
}