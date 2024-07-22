package coreDomain.directory

import DeviceType
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coreDomain.shared.Employee
import coreDomain.shared.ImageRes
import coreDomain.shared.ScreenState
import genericDomain.dependencyInjection.KoinInjector
import genericDomain.imageLoader.CoilImage
import getDeviceType
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun DirectoryScreen(isSufficientMemory: () -> Boolean) {
    val directoryViewModel: DirectoryViewModel = KoinInjector.inject()
    val screenState = directoryViewModel.state.collectAsState()
    LaunchedEffect(true) {
        directoryViewModel.getEmployees(isSufficientMemory)
    }
    when (screenState.value) {
        ScreenState.Loading -> {
            DirectoryLoadingLayout()
        }

        ScreenState.Ready -> {
            DirectoryScreenLayout(
                employees = directoryViewModel.employees.value,
                isSufficientMemory = isSufficientMemory
            )
        }

        ScreenState.Error -> {
            DirectoryErrorLayout(
                retry = { directoryViewModel.getEmployees(isSufficientMemory) }
            )
        }
    }

}

@Composable
private fun DirectoryLoadingLayout() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading")
    }
}

@Composable
private fun DirectoryScreenLayout(employees: List<Employee>, isSufficientMemory: () -> Boolean) {
    Column(Modifier.fillMaxWidth()) {
        if (getDeviceType() == DeviceType.ANDROID) {
            TopAppBar(content = {
                Text(
                    text = "Employee Directory",
                    color = MaterialTheme.typography.body1.color
                )
            })
        }
        if (employees.isNotEmpty()) {
            if (getDeviceType() == DeviceType.IPHONE) {
                Spacer(modifier = Modifier.height(48.dp))
                EmployeeDirectory(employees = employees, isSufficientMemory = isSufficientMemory)
            } else {
                Box {
                    EmployeeDirectory(
                        employees = employees,
                        isSufficientMemory = isSufficientMemory
                    )
                    Column(
                        modifier = Modifier.fillMaxSize().padding(bottom = 24.dp, end = 16.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        RefreshButton(isSufficientMemory = isSufficientMemory)
                    }
                }
            }
        } else {
            EmptyDirectory()
        }
    }
}

@Composable
@OptIn(ExperimentalResourceApi::class)
private fun DirectoryErrorLayout(retry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(175.dp),
            painter = painterResource(ImageRes.wifi_off),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Error")
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            modifier = Modifier.clickable { retry() },
            text = "Retry",
            color = Color.Blue,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
private fun EmployeeDirectory(employees: List<Employee>, isSufficientMemory: () -> Boolean) {
    LazyColumn(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        if (getDeviceType() == DeviceType.IPHONE) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Employee Directory",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    RefreshButton(isSufficientMemory)
                }
            }
        }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        items(employees) { employee ->
            EmployeeCard(employee)
            Spacer(modifier = Modifier.height(12.dp))
        }
        item { Spacer(modifier = Modifier.height(12.dp)) }
    }
}

@Composable
@OptIn(ExperimentalResourceApi::class)
private fun RefreshButton(isSufficientMemory: () -> Boolean) {
    val directoryViewModel: DirectoryViewModel = KoinInjector.inject()
    FloatingActionButton(onClick = {
        directoryViewModel.getEmployees(isSufficientMemory)
    }) {
        Image(
            painter = painterResource(ImageRes.refresh),
            contentDescription = null
        )
    }
}

@Composable
@OptIn(ExperimentalResourceApi::class)
private fun EmptyDirectory() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(175.dp),
            painter = painterResource(ImageRes.team),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Sit Tight")
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "We're Building an Awesome Team")
        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
private fun EmployeeCard(employee: Employee) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CoilImage(
                    modifier = Modifier.size(100.dp).clip(CircleShape),
                    placeHolder = ImageRes.placeholder,
                    url = employee.photoUrlSmall ?: ""
                )
                Spacer(modifier = Modifier.width(4.dp))
                Column {
                    Text(
                        text = employee.fullName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = employee.team,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}
