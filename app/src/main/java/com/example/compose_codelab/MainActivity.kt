package com.example.compose_codelab

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_codelab.ui.theme.Blue200
import com.example.compose_codelab.ui.theme.Compose_CodelabTheme
import com.example.compose_codelab.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_CodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    MyColumn(modifier = Modifier.padding(15.dp))
                    ContentView()

                }
            }
        }
    }
}

@Composable
fun ContentView(){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {
            Log.d("TAG", "ContentView: 온보딩 클릭됨")
            shouldShowOnboarding = !shouldShowOnboarding
        })
    } else {
//        MyColumn(modifier = Modifier.padding(20.dp))
        MyLazyColumn(modifier = Modifier.padding(20.dp))

    }


//    Surface(color = Blue200) {
//        MyCoiumn()
//    }
}

@Composable
fun MyColumn(
    modifier: Modifier = Modifier,
    fruits: List<String> = listOf("Banana", "Apple","Strawberry")
) {
    Column(modifier) {
        for (name in fruits) {
            FruitView(name = name)
        }
    }
}

@Composable
fun MyLazyColumn(fruits: List<String> = List(1000) { "번호 : $it" } , modifier: Modifier) {
    LazyColumn (modifier = modifier) {
        items(items = fruits) { name ->
            FruitView(name = name)
        }
    }
}

@Composable
fun FruitView(name:String){

    val expanded = rememberSaveable { mutableStateOf(false) }

    var isOpen by remember { mutableStateOf(false) }

    val (shouldOpen, setShouldOpen) = remember { mutableStateOf(false) }

//    val extraPadding = if (expanded.value) 48.dp else 0.dp

    val extraPadding by animateDpAsState(
        if (expanded.value) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
    )

    Surface(color = Teal200
        ,modifier = Modifier
            .clickable { Log.d("Tag", " FruitVIEW : $name") }
            .padding(bottom = extraPadding))
    {
        Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp).fillMaxWidth(1.0f)
        ) {
            Text(text = name,
                modifier = Modifier
                    .padding(20.dp)
//                    .fillMaxWidth(1.0f)
                    .background(Color.Red)
                    .weight(0.5f),
                    style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                        )
            )
            OutlinedButton(
//                modifier = Modifier.weight(0.5f),
                onClick = {
                    expanded.value = !expanded.value
//                    isOpen = !isOpen
//                    setShouldOpen(!shouldOpen)

                }) {
                Text(text = if (expanded.value) "열렸다" else "닫혔다")
//                Text(text = if (isOpen) "열렸다" else "닫혔다")
//                Text(text = if (shouldOpen) "열렸다" else "닫혔다")

            }
        }
    }

}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    // TODO: This state should be hoisted
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_NO,
    name = "DefaultPreviewDark"
)
@Composable
fun DefaultPreview() {
    Compose_CodelabTheme {
//        MyColumn()
        ContentView()

    }
}