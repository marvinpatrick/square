import coreDomain.directory.DirectoryViewModel
import org.junit.Assert
import org.junit.Test

class DirectoryUnitTest : BaseKoinTest() {

    private val directoryViewModel: DirectoryViewModel = testInject()

    @Test
    fun `given a non malformed list of EMPLOYYES then return that list`() {
        directoryViewModel.getEmployees()
        Assert.assertEquals(0, directoryViewModel.employees.value?.size)
    }

}