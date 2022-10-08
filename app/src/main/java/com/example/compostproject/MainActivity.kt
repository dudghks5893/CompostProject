package com.example.compostproject

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.compostproject.ui.theme.CompostProjectTheme
import com.example.compostproject.ui.theme.MyGreen
import com.example.compostproject.ui.theme.Typography
import com.example.compostproject.utils.DummyDataProvider
import com.example.compostproject.utils.RandomUser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 그려지는 내용
        setContent {
            CompostProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ContentView()
//                    RandomUserListView(randomUsers = DummyDataProvider.userList)
                }
            }
        }
    }
}

@Composable
fun MyAppBar() {
    TopAppBar(
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.primary,
//        backgroundColor = MyGreen,
        modifier = Modifier.height(58.dp)
    ) {
        Text(
            text = stringResource(id = R.string.top_bar_text),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            fontSize = 18.sp,
            fontWeight = FontWeight.Black

        )
    }
}

@Composable
fun ContentView() {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(backgroundColor = Color.White,
            topBar = { MyAppBar() }
        ) {
            RandomUserListView(randomUsers = DummyDataProvider.userList)
        }
    }
}

@Composable
fun RandomUserListView(randomUsers: List<RandomUser>) {
    // 메모리 관리가 들간 LazyColumn
    LazyColumn() {
//        items(randomUsers) { aRandomUser -> Text(text = aRandomUser.name) }
        items(randomUsers) { RandomUserView(it) }
    }
}

@Composable
fun RandomUserView(randomUser: RandomUser) {
    val typography = MaterialTheme.typography
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
//            Box(
//                modifier = Modifier
//                    .size(width = 60.dp, height = 60.dp)
//                    .clip(CircleShape)
//                    .background(Color.Red)
//            )
            ProfileImg(imgUrl = randomUser.profileImageUrl)
            Column() {
                Text(
                    text = randomUser.name,
                    style = typography.subtitle1
                )
                Text(
                    text = randomUser.description,
                    style = typography.body1
                )
            }
        }
    }
}

@Composable
fun ProfileImg(imgUrl: String, modifier: Modifier = Modifier) {
    // 이미지 비트맵
    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }

    // 이미지 모디아피어
    val imageModifier = modifier
        .size(50.dp, 50.dp)
//        .clip(RoundedCornerShape(10.dp))
        .clip(CircleShape)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}

        })

    // 비트 맵이 있다면
    bitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
        Image(
            bitmap = fetchedBitmap,
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = imageModifier
        )
    } ?: Image(
        painter = painterResource(id = R.drawable.ic_empty_user_img),
        contentScale = ContentScale.Fit,
        contentDescription = null,
        modifier = imageModifier
    )

}


// 미리보기 뷰
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompostProjectTheme {
//        Greeting("미리보기")
        ContentView()
//        RandomUserListView(randomUsers = DummyDataProvider.userList)
    }
}