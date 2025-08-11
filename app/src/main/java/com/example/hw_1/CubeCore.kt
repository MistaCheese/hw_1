package com.example.hw_1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hw_1.ui.theme.CubeUi

class CubeCore {

    @Composable
    fun Cube(
        cubeNumber: String,
        cubeColor: Color,
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(15.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(18.dp))
                .background(cubeColor, RoundedCornerShape(18.dp))

        ) {
            Text(
                cubeNumber,
                modifier = Modifier
                    .align(Alignment.Center),
                fontSize = 50.sp,
                color = Color.White
            )
        }
    }
}

fun setCubeColor(cubeIndex: Int): Color {
    return if (cubeIndex % 2 == 0) Color.Red else Color.Blue
}