package coreDomain.directory

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Text
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
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun DirectoryScreen() {
    val directoryViewModel: DirectoryViewModel = KoinInjector.inject()
    val screenState = directoryViewModel.state.collectAsState()
    LaunchedEffect(true) {
        directoryViewModel.getEmployees()
    }
    when (screenState.value) {
        ScreenState.Loading -> {
            DirectoryLoadingLayout()
        }

        ScreenState.Ready -> {
            DirectoryScreenLayout(directoryViewModel.employees.value)
        }

        ScreenState.Error -> {
            DirectoryErrorLayout(
                retry = {
                    directoryViewModel.getEmployees()
                }
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
@OptIn(ExperimentalResourceApi::class)
private fun DirectoryScreenLayout(employees: List<Employee>) {
    val directoryViewModel: DirectoryViewModel = KoinInjector.inject()
    Column(Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Employee Directory", fontSize = 16.sp, fontWeight = FontWeight.Light)
            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(onClick = { directoryViewModel.getEmployees() }) {
                Image(painter = painterResource(ImageRes.refresh), contentDescription = null)
            }
        }
        if (employees.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            EmployeeDirectory(employees)
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
private fun EmployeeDirectory(employees: List<Employee>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(employees) { employee ->
            EmployeeCard(employee)
        }
        item { Spacer(modifier = Modifier.height(12.dp)) }
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
