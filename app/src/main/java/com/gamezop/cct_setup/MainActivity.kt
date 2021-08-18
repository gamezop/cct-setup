package com.gamezop.cct_setup

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContentProviderCompat.requireContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO STEP 2
        val cctButton = findViewById<Button>(R.id.openGamezopButton)
        cctButton.setOnClickListener {
            val url = "https://www.gamezop.com/?id=xyz"; //BE SURE TO INSERT YOUR GAMEZOP PUB ID INSTEAD OF xyz HERE
            val customTabsIntent: CustomTabsIntent = CustomTabsIntent.Builder().build()
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }
    }
}