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

        directoryViewModel.getEmployees(isSufficientMemory = { true })

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

        directoryViewModel.getEmployees(isSufficientMemory = { true })

        Assert.assertEquals(
            "The list is short or large ${directoryViewModel.employees.value.toList()}",
            2,
            directoryViewModel.employees.value.size
        )
        Assert.assertTrue(
            "$stubEmployee didn't make the list",
            directoryViewModel.employees.value.contains(stubEmployee)
        )
        Assert.assertTrue(
            "$marvinPatrick didn't make the list",
            directoryViewModel.employees.value.contains(marvinPatrick)
        )
    }

    @Test
    fun `given a low memory with many EMPLOYEES ensure none are returned`() = runTest {
        val stubEmployee = EmployeeBuilder().build()
        val marvinPatrick = EmployeeBuilder().withFullName("Marvin Patrick").build()
        val fakeDirectory = directoryRepoBuilder
            .withEmployee(stubEmployee)
            .withEmployee(marvinPatrick)
            .build()
        directoryViewModel = DirectoryViewModel(fakeDirectory)

        directoryViewModel.getEmployees(isSufficientMemory = { false })

        Assert.assertEquals(
            "The list is short or large ${directoryViewModel.employees.value.toList()}",
            0,
            directoryViewModel.employees.value.size
        )
        Assert.assertFalse(
            "$stubEmployee made the list when it shouldn't have",
            directoryViewModel.employees.value.contains(stubEmployee)
        )
        Assert.assertFalse(
            "$marvinPatrick made the list when it shouldn't have",
            directoryViewModel.employees.value.contains(marvinPatrick)
        )
    }

}