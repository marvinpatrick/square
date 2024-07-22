package directory

import coreDomain.directory.DirectoryViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import testBuilders.DirectoryRepoBuilder
import testBuilders.EmployeeBuilder

class DirectoryUnitTest {

    private val directoryRepoBuilder = DirectoryRepoBuilder()

    private lateinit var directoryViewModel: DirectoryViewModel

    @Test
    fun `given a list of 0 EMPLOYYES then return an empty list`() = runTest {
        val fakeDirectory = directoryRepoBuilder.withNoEmployees().build()
        directoryViewModel = DirectoryViewModel(fakeDirectory)

        directoryViewModel.getEmployees()

        Assert.assertEquals(0, directoryViewModel.employees.value.size)
    }

    @Test
    fun `given a response with many EMPLOYEES ensure they are all returned`() = runTest {
        val stubEmployee = EmployeeBuilder().build()
        val marvinPatrick = EmployeeBuilder().withFullName("Marvin Patrick").build()
        val fakeDirectory = directoryRepoBuilder
            .withEmployee(stubEmployee)
            .withEmployee(marvinPatrick)
            .build()
        directoryViewModel = DirectoryViewModel(fakeDirectory)

        directoryViewModel.getEmployees()

        Assert.assertEquals(
            "The list is short or large ${directoryViewModel.employees.value.toList()}",
            2,
            directoryViewModel.employees.value.size
        )
        Assert.assertEquals(
            "$stubEmployee didn't make the list",
            stubEmployee,
            directoryViewModel.employees.value.getOrNull(0)
        )
        Assert.assertEquals(
            "$marvinPatrick didn't make the list",
            marvinPatrick,
            directoryViewModel.employees.value.getOrNull(1)
        )
    }

}