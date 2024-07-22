package coreDomain.directory

import coreDomain.shared.Employee
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

    private val _employees = MutableStateFlow<List<Employee>>(emptyList())
    val employees: StateFlow<List<Employee>> = _employees

    fun getEmployees() {
        _state.value = ScreenState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            val result = directoryRepo.getEmployees()
            _employees.emit(result ?: emptyList())
            _state.emit(ScreenState.Ready)
        }
    }

}