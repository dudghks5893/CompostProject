package com.example.compostproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compostproject.ui.theme.CompostProjectTheme

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
                    Greeting("안녕?")
                }
            }
        }
    }
}

// 뷰
@Composable
fun Greeting(name: String) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "TopAppBar") }, backgroundColor = Color(0xFF3E7AAF))
    },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Text("클릭")
            }
        }
    ) {
//        Text(text = "안녕하세요! $name!")
//        MyComposableView()
        MyColumn()
    }
}


@Composable
fun MyRowItem(){
    Log.d("TAG","MyRowItem: ")
    Row(
        Modifier
            .padding(10.dp)
            .background(Color(0x90FA6B6B))
            .fillMaxWidth(), // 풀 너비
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 패딩과 백드라운드 순서에 따라 패딩에도 색을 포함 또는 미포함
        Text(text = "리무르",
            Modifier
                .padding(all = 5.dp)
                .background(Color.Yellow))
        // 스페이스
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "3기는")
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "언제하나요")
    }
}

@Composable
fun MyColumn() {
    Column(
        Modifier
            .background(Color.Green)
            .verticalScroll(rememberScrollState())
    ) {
        for (i in 0..30) {
            MyRowItem()
        }
    }
}


// Composable 네이밍은 대문자로 시작한다 자체가 뷰 이기 때문이다.
@Composable
fun MyComposableView() {
    Log.d("TAG","MyComposableView: ")
    // 버티칼 리니어
    Column(
        Modifier.background(Color.Gray)
    ) {
        // 호리젠탈 리니어
        Row(
            Modifier
                .padding(10.dp)
                .background(Color(0x90FA6B6B)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 패딩과 백드라운드 순서에 따라 패딩에도 색을 포함 또는 미포함
            Text(text = "리무르",
                Modifier
                    .padding(all = 5.dp)
                    .background(Color.Yellow))
            // 스페이스
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "3기는")
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "언제하나요")
        }
        // 호리젠탈 리니어
        Row(
            Modifier
                .padding(10.dp)
                .background(Color(0x90FA6B6B)),
            verticalAlignment = Alignment.Top
        ) {
            // 패딩과 백드라운드 순서에 따라 패딩에도 색을 포함 또는 미포함
            Text(text = "리무르",
                Modifier
                    .background(Color.Yellow)
                    .padding(all = 5.dp))
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "3기는")
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "언제하나요")
        }
        // 호리젠탈 리니어
        Row(
            Modifier
                .padding(10.dp)
                .background(Color(0x90FA6B6B)),
            verticalAlignment = Alignment.Top
        ) {
            Text(text = "리무르",
                Modifier
                    .background(Color.Yellow)
                    .padding(horizontal = 5.dp))
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "3기는")
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "언제하나요")
        }
    }

}
// 미리보기 뷰
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompostProjectTheme {
        Greeting("미리보기")
    }
}