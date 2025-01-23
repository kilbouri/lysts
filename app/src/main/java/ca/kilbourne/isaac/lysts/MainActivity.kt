package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.ui.theme.LystsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val coroutineScope = rememberCoroutineScope()
            val allLists by viewModel.todoLists.getAll()
                .collectAsStateWithLifecycle(initialValue = listOf())

            LystsTheme {
                Scaffold {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .padding(16.dp, 16.dp)
                    ) {
                        Row {
                            Button(onClick = {
                                coroutineScope.launch {
                                    viewModel.todoLists.create(
                                        TodoList(
                                            name = allLists.size.toString()
                                        )
                                    )
                                }
                            }) {
                                Text("Add List")
                            }
                        }

                        HorizontalDivider(modifier = Modifier.padding(8.dp))

                        LazyColumn {
                            itemsIndexed(allLists) { index, item ->

                                Button(onClick = {
                                    coroutineScope.launch { viewModel.todoLists.delete(item.id!!) }
                                }) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "Delete " + item.name
                                    )
                                    Text(item.name)
                                }
                            }
                        }
                    }
                }

//                MainActivityPresentation(
//                    todoLists = allLists,
//                    allLists.firstOrNull() ?: TodoList(
//                        "...",
//                    )
//                )
            }
        }
    }
}
