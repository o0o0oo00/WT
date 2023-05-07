package com.example.wt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wt.ui.theme.WTTheme
import com.example.wt.viewmodel.Cast
import com.example.wt.viewmodel.Weather
import com.example.wt.viewmodel.WeatherViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by inject<WeatherViewModel>()
        viewModel.getWeatherList(CityList.map { it.code })

        setContent {
            WTTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Column {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "明日天气预报",
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.subtitle1
                        )
                        val citiesStateFlow by viewModel.citiesStateFlow.collectAsState()
                        LazyColumn {
                            items(citiesStateFlow) {
                                it.firstOrNull()?.also {
                                    CitySummary(weather = it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CitySummary(weather: Weather) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(10.dp)
        .background(Color.White)
        .clickable { isExpanded = !isExpanded }) {
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            WeatherInformation(weather, weather.casts[1])
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "更多天气",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle1
            )
            Box(modifier = Modifier
                .padding(10.dp)
                .clickable { isExpanded = !isExpanded }) {
                val rotationValue by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)
                Image(
                    painter = painterResource(id = R.drawable.baseline_expand_more_24),
                    contentDescription = "more",
                    modifier = Modifier.graphicsLayer(rotationZ = rotationValue)
                )
            }
        }
        if (isExpanded) {
            Column(modifier = Modifier.padding(10.dp, 0.dp)) {
                WeatherInformation(weather = weather, cast = weather.casts[2])
                WeatherInformation(weather = weather, cast = weather.casts[3])
            }
        }
    }

}

@Composable
private fun WeatherInformation(
    weather: Weather, cast: Cast
) {
    Column {
        Text(
            text = "${weather.province}：${weather.city} 星期${cast.week}",
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            text = "白天:${cast.dayweather} 温度 ${cast.daytemp} 风速 ${cast.daywind} 风力 ${cast.daypower}",
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            text = "夜晚:${cast.nightweather} 温度 ${cast.nighttemp} 风速 ${cast.nightwind} 风力 ${cast.nightpower}",
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.subtitle2
        )
    }
}