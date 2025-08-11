package com.example.hw_1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hw_1.ui.theme.CubeUi
import com.example.hw_1.ui.theme.Typography

// MainActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                CubesScreen()
            }
        }
    }
}

@Composable
fun CubesScreen() {
    val cubesState = rememberSaveable { mutableStateOf(mutableListOf<CubeUi>()) }
    val columnIndex = rememberSaveable { mutableStateOf(0) }
    val conf = LocalConfiguration.current
    val columnsCount =
        remember { if (conf.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 3 }     // Определяем количество колонок в зависимости от ориентации

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            items(count = cubesState.value.size / columnsCount + 1) { rowIndex ->
                Row {
                    repeat(columnsCount) { column ->
                        val cubeIndex = rowIndex * columnsCount + column
                        if (cubeIndex < cubesState.value.size) {
                            Column(
                                Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CubeCore().Cube(
                                    cubeNumber = (cubeIndex + 1).toString(),
                                    cubeColor = if (cubeIndex % 2 == 0) Color.Red else Color.Blue
                                )
                            }
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }

        // Кнопка добавления кубика
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {
                        val newCube = CubeUi()
                        cubesState.value =
                            (cubesState.value + mutableListOf(newCube)).toMutableList()
                        columnIndex.value = (columnIndex.value + 1) % columnsCount
                    },
                ) {
                    Text(stringResource(R.string.add_cube_button))
                }
            }
        }
    }
}

@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography,
        content = content
    )
}


@Preview
@Composable
fun PreviewBoxContainer() {
    CubesScreen()
}