package com.imdmp.androidrapidprototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.imdmp.androidrapidprototype.ui.theme.AndroidRapidPrototypeTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidRapidPrototypeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val listData = remember{ mutableStateListOf<VideoDataItem>()}

                    LaunchedEffect(key1 = Unit){
                        val retrofit = Retrofit.Builder()

                            .baseUrl(BuildConfig.BASE_API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        val sampleService = retrofit.create(SampleService::class.java)

                        val playlist = sampleService.getPlaylistData()

                        Timber.d("" + playlist)

                        listData.addAll(playlist)
                    }

                    LazyColumn(modifier = Modifier.fillMaxSize()){
                        items(listData){
                            Box(modifier = Modifier.clickable {
                                openActivity(it.video_url)
                            }) {
                                Text(it.description)
                            }
                        }
                    }


                }
            }
        }
    }
}

fun openActivity(url:String){
//    startActivity(String)
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidRapidPrototypeTheme {
        Greeting("Android")
    }
}