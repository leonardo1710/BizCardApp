package com.example.bizcardapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bizcardapp.models.Project
import com.example.bizcardapp.ui.theme.BizCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    BizCard()
                }
            }
        }
    }
}

@Composable
fun BizCard() {
    val showPortfolio = remember {
        mutableStateOf(false)
    }

    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                CustomImage(modifier = Modifier.size(150.dp))

                Divider()

                ProfileInfo()

                Button(
                    shape = RectangleShape,
                    onClick = {
                        showPortfolio.value = !showPortfolio.value
                    }
                ) {
                    Text("Show Projects",
                        style = MaterialTheme.typography.labelMedium)
                }

                Divider(modifier = Modifier.padding(4.dp))

                AnimatedVisibility(visible = showPortfolio.value) {
                    Portfolio(projects = listOf(
                        Project("Project 1", "super awesome project 1"),
                        Project("Project 2", "super awesome project 2"),
                        Project("Project 3", "super awesome project 3"),
                        Project("Project 4", "super awesome project 4"),
                        Project("Project 5", "super awesome project 5"),
                    ))
                }
            }
        }
    }
}

@Composable
fun Portfolio(projects: List<Project>) {
    LazyColumn {
        items(projects) { project ->
            Card(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
                .clickable {  Log.d("card", "onClick") },
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Row(modifier = Modifier.padding(8.dp)) {

                    CustomImage(modifier = Modifier.size(80.dp))

                    Column( modifier = Modifier
                        .padding(8.dp)
                        .align(alignment = Alignment.CenterVertically)) {

                        Text(text = project.name, fontWeight = FontWeight.Bold)
                        Text(text = project.description,
                            style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileInfo() {
    Column(modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Fuma Kotaro",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Red
        )
        Row {
            Text(text = "Android Compose Programmer", modifier = Modifier.padding(3.dp))
            Text(text = "@nightNinja", modifier = Modifier.padding(3.dp), style = MaterialTheme.typography.titleSmall)
        }
    }
}


@Composable
fun CustomImage(modifier: Modifier = Modifier) {
    Surface(modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp
    ) {

        Image(painter = painterResource(id = R.drawable.robot),
            contentDescription = "profile image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop)

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardAppTheme {
        BizCard()
    }
}