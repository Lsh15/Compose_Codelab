package com.example.compose_codelab

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import coil.compose.rememberImagePainter
import com.example.compose_codelab.ui.theme.*
import kotlinx.coroutines.launch

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
                TwoTexts(modifier = Modifier,"오늘도", "빡코딩")
                }
            }
        }
    }

@Composable
fun ContentView() {
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
    fruits: List<String> = listOf("Banana", "Apple", "Strawberry")
) {
    Column(modifier) {
        for (name in fruits) {
            FruitView(name = name)
        }
    }
}

@Composable
fun MyLazyColumn(fruits: List<String> = List(1000) { "번호 : $it" }, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = fruits) { name ->
            FruitView(name = name)
        }
    }
}

@Composable
fun FruitView(name: String) {

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

    Surface(color = Teal200, modifier = Modifier
        .clickable { Log.d("Tag", " FruitVIEW : $name") }
        .padding(bottom = extraPadding))
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp).fillMaxWidth(1.0f)
        ) {
            Text(
                text = name,
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

@Composable
fun PhotographerCard(
    modifier: Modifier = Modifier,
    contentSlot: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Blue200)
            .padding(all = 10.dp)
            .height(300.dp)
    ) {
        Surface(
            modifier = Modifier
                .size(80.dp)
                .weight(0.3f)
                .clickable { Log.d("TAG", "PhotographerCard: 클릭") }
                .clip(RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp))
                .background(Color.Red)
                .padding(10.dp)
                .background(Color.Magenta)
                .padding(10.dp)
                .background(Color.Yellow),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)) {
        }

        Surface(
            modifier = Modifier
                .background(Color.Yellow)
                .weight(0.7f)
                .fillMaxHeight(),
            content = contentSlot
        )

//        Column(
//            verticalArrangement = Arrangement.spacedBy(3.dp),
//            modifier = Modifier
//                .background(Color.Yellow)
//                .weight(0.7f)
//                .fillMaxHeight()
//        ){
//            Text("Alfred Sisley", fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .background(Purple500)
//                    .weight(1f))
//            // LocalContentAlpha is defining opacity level of its children
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Text("3 minutes ago", style = MaterialTheme.typography.body2,
//                    modifier = Modifier
//                        .background(Color.Green)
//                        .weight(2f))
//            }
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
//                Text("이용불가", style = MaterialTheme.typography.caption,
//                    modifier = Modifier
//                        .background(Purple200)
//                        .weight(2f))
//            }
//        }
    }
}

@Composable
fun LayoutsCodelab() {

    val coroutineScope = rememberCoroutineScope()

    val scrollState = rememberScrollState()
    val lazyListScrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = {
                        Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
                    }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }

                    IconButton(onClick = {
                        Log.d("TAG", "LayoutsCodelab: 홈 클릭")
                    }) {
                        Icon(Icons.Filled.Home, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar() {
                IconButton(onClick = {
                    Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
                }) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }

                IconButton(onClick = {
                    Log.d("TAG", "LayoutsCodelab: 홈 클릭")
                }) {
                    Icon(Icons.Filled.Home, contentDescription = null)
                }
            }
        },

        drawerContent = {
            IconButton(onClick = {
                Log.d("TAG", "LayoutsCodelab: 좋아요 클릭")
            }) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }

            IconButton(onClick = {
                Log.d("TAG", "LayoutsCodelab: 홈 클릭")
            }) {
                Icon(Icons.Filled.Home, contentDescription = null)
            }
        },
        floatingActionButton = {
            IconButton(onClick = {
                Log.d("TAG", "LayoutsCodelab: 홈 클릭")
                coroutineScope.launch {
//                    scrollState.scrollTo(0)
//                    scrollState.animateScrollTo(0)
                    lazyListScrollState.animateScrollToItem(0)
                }
            }) {
                Icon(Icons.Filled.ArrowForward, contentDescription = null)
            }
        }
    ) { innerPadding ->
        BodyContent(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp), contentSlot = {
//            SomeText()
//                SimpleList(scrollState)
                    LazySimpleList(scrollState = lazyListScrollState)

            })
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "",
            modifier = Modifier.size(50.dp)
        )
//        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun SimpleList(scrollState : ScrollState) {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
//    val scrollState = rememberScrollState()

    val getBGColor : (Int) -> Color = { index ->
        if (index == 0) Color.Red else Color.Yellow
    }

    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100) {
            Text("Item #$it",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(getBGColor(it))
            )
        }
    }
//    Column() {
//        repeat(100) {
//            Text("Item #$it")
//        }
//    }
}

@Composable
fun LazySimpleList(scrollState : LazyListState) {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
//    val scrollState = rememberScrollState()

    val getBGColor : (Int) -> Color = { index ->
        if (index == 0) Color.Red else Color.Yellow
    }

    LazyColumn(state = scrollState) {
        items(100) {
//            Text("Item #$it",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(getBGColor(it))
//            )
            ImageListItem(it)
        }
    }
//    Column() {
//        repeat(100) {
//            Text("Item #$it")
//        }
//    }
}

@Composable
fun SomeText(){
    Column() {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
        Text(text = "Thanks for going through the Layouts codelab")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier, contentSlot : @Composable () -> Unit) {

    Surface(
        color = Blue200,
        modifier = modifier,
        content = contentSlot
    )

//    Surface(
//        color = Blue200,
//        modifier = modifier,
//        content = {
//            Column() {
//                Text(text = "Hi there!")
//                Text(text = "Thanks for going through the Layouts codelab")
//            }
//        }
//    )
//    Surface() {
//        Column(
//            modifier = modifier.background(Blue200)
//        ) {
//            Text(text = "Hi there!")
//            Text(text = "Thanks for going through the Layouts codelab")
//        }
//    }
}

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier
        .background(Color.Yellow)
        .height(IntrinsicSize.Min)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .height(100.dp)
                .wrapContentWidth(Alignment.Start),
            text = "asdfasdfasdfasdfasdf"
        )

        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),

            text = text2
        )
    }
}

@Composable
fun DecoupledConstraintLayout() {

    val buttonId : String = "MyButton"
    val addingMarginBtnId : String = "AddingMarginBtnId"
    val textId : String = "MyText"

    // 추가된 마진
    val addedMargin = remember{ mutableStateOf(0.dp) }

    val animatedMargin = animateDpAsState(
        targetValue = addedMargin.value,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(
                addingMarginBtnId,
                buttonId,
                textId,
                margin = 16.dp,
                addedMargin = animatedMargin.value) // Portrait constraints
        } else {
            decoupledConstraints(
                addingMarginBtnId,
                buttonId,
                textId,
                margin = 100.dp,
                addedMargin = animatedMargin.value
            ) // Landscape constraints
        }
        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId(buttonId)
            ) {
                Text("Button")
            }
            Button(
                modifier = Modifier.layoutId(addingMarginBtnId),
                onClick = {
                    addedMargin.value = addedMargin.value + 100.dp
                }) {
                Text(text = "마진 추가 버튼")
            }
            Text("Text", Modifier.layoutId(textId))
        }
    }
}


private fun decoupledConstraints(addingMarginBtnId: String,
                                 buttonId: String,
                                 textId: String,
                                 margin: Dp, addedMargin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor(buttonId)
        val text = createRefFor(textId)
        val addingMarginBtn = createRefFor(addingMarginBtnId)

        constrain(button) {
            top.linkTo(parent.top, margin= margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
        constrain(addingMarginBtn){
            top.linkTo(text.bottom, margin = addedMargin)
        }
    }
}

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout(
        modifier = Modifier.background(Color.Yellow)
    ) {
        val text = createRef()

        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(
            "This is a long long long long long long long text",
            Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)
                width = Dimension.preferredWrapContent.atLeast(100.dp)
            }
        )
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        // Creates references for the three composables
        // in the ConstraintLayout's body
        val (button1, button2, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button 1")
        }

        Text("안녕하세요!", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end)
        })

        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text("Button 2")
        }
    }

//    ConstraintLayout (
//        modifier = Modifier.background(Color.Yellow).fillMaxSize()
//    ){
//
//        // Create references for the composables to constrain
//
//        val (buttonRef, textRef, someTextRef) = createRefs()
//
//        Button(
//            onClick = { /* Do something */ },
//            // Assign reference "button" to the Button composable
//            // and constrain it to the top of the ConstraintLayout
//            modifier = Modifier.constrainAs(buttonRef) {
////                top.linkTo(parent.top, margin = 100.dp)
////                start.linkTo(parent.start)
//                centerHorizontallyTo(parent)
//            }
//        ) {
//            Text("Button")
//        }
//
////        // Assign reference "text" to the Text composable
////        // and constrain it to the bottom of the Button composable
//        Text("Text", Modifier.constrainAs(textRef) {
////            top.linkTo(buttonRef.bottom, margin = 16.dp)
//            start.linkTo(buttonRef.end, margin = 30.dp)
//        })
//
//        Text("SomeText", Modifier.constrainAs(someTextRef) {
////            top.linkTo(button.bottom, margin = 16.dp)
//            // Centers Text horizontally in the ConstraintLayout
//            centerHorizontallyTo(buttonRef)
////            top.linkTo(buttonRef.bottom, margin = 16.dp)
//            centerVerticallyTo(parent)
//        })
//    }
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
        TwoTexts(modifier = Modifier,"오늘도", "빡코딩")
        }
    }
}