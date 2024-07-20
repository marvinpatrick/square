import businessDomain.directory.DirectoryViewModel
import org.junit.Assert
import org.junit.Test

class DirectoryUnitTest {

    private val directoryViewModel = DirectoryViewModel()

    @Test
    fun `given a non malformed list of EMPLOYYES then return that list`() {
        Assert.assertEquals(1, directoryViewModel.getListOfEmployees().size)
    }

}