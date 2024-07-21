import businessDomain.directory.DirectoryViewModel
import org.junit.Assert
import org.junit.Test

class DirectoryUnitTest : BaseKoinTest() {

    private val directoryViewModel: DirectoryViewModel = testInject()

    @Test
    fun `given a non malformed list of EMPLOYYES then return that list`() {
        Assert.assertEquals(0, directoryViewModel.getListOfEmployees().size)
    }

}