package directory

import coreDomain.directory.DirectoryViewModel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Test
import testBuilders.DirectoryRepoBuilder
import testBuilders.EmployeeBuilder

class DirectoryUnitTest {

    private var directoryViewModel: DirectoryViewModel? = null

    @After
    fun tearDown() {
        directoryViewModel = null
    }

    @Test
    fun `given a list of 0 EMPLOYYES then return an empty list`() = runTest {
        val directoryRepoBuilder = DirectoryRepoBuilder()
        val fakeDirectory = directoryRepoBuilder.withNoEmployees().build()
        directoryViewModel = DirectoryViewModel(fakeDirectory)

        directoryViewModel?.getEmployees()

        Assert.assertEquals(0, directoryViewModel?.employees?.value?.size)
    }

    @Test
    fun `given a response with many EMPLOYEES ensure they are all returned`() = runTest {
        val directoryRepoBuilder = DirectoryRepoBuilder()
        val employeeBuilder = EmployeeBuilder()
        val stubEmployee = employeeBuilder.build()
        val marvinPatrick = employeeBuilder.withFullName("Marvin Patrick").build()
        val fakeDirectory = directoryRepoBuilder
            .withEmployee(stubEmployee)
            .withEmployee(marvinPatrick)
            .build()
        directoryViewModel = DirectoryViewModel(fakeDirectory)

        directoryViewModel?.getEmployees()

        Assert.assertEquals(2, directoryViewModel?.employees?.value?.size)
        Assert.assertEquals(stubEmployee, directoryViewModel?.employees?.value?.getOrNull(0))
        Assert.assertEquals(marvinPatrick, directoryViewModel?.employees?.value?.getOrNull(1))
    }

}