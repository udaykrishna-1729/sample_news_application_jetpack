package com.example.notificationsproject.ui.theme

import coil.compose.AsyncImage


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class NewsDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = intent.getStringExtra("title") ?: ""
        val desc = intent.getStringExtra("description") ?: ""
        val img = intent.getStringExtra("imageUrl")
        val published = intent.getStringExtra("published") ?: ""
        val url = intent.getStringExtra("fullArticleUrl") ?: ""

        setContent {
            MaterialTheme {
                Column(modifier = Modifier .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()))   {

                    AsyncImage(
                        model = img,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(250.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = title, style = MaterialTheme.typography.titleLarge)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Published On: $published", style = MaterialTheme.typography.bodySmall)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = desc, textAlign = TextAlign.Justify)

                    Spacer(modifier = Modifier.height(20.dp))

                    val context = LocalContext.current

                    Button(onClick = {
                        val intent = Intent(context, WebViewActivity::class.java)
                        intent.putExtra("web_url", url)
                        context.startActivity(intent)
                    }) {
                        Text("Read Full Article")
                    }
                }
            }
        }
    }
}
