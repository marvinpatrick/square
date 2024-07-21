package coreDomain.directory

import coreDomain.shared.ScreenState
import genericDomain.viewModel.BaseKMMViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DirectoryViewModel(private val directoryRepo: DirectoryRepo) : BaseKMMViewModel() {

    private val _state = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val state: StateFlow<ScreenState> = _state

    val employees: MutableStateFlow<List<Employee>?> = MutableStateFlow(null)

    fun getEmployees() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = directoryRepo.getEmployees()
            employees.emit(result)
            _state.value = ScreenState.Ready
        }
    }

}