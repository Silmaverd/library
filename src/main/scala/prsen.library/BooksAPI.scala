package prsen.library

import io.swagger.annotations._
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation._
import prsen.library.database.jdbc.BookPersistanceService
import prsen.library.model.book.BookView

@RestController
@RequestMapping(path = Array("/api/library"))
@Api(value = "Library Managment API")
class BooksAPI(bookPersistanceService: BookPersistanceService) {
    
    private val log = LoggerFactory.getLogger(getClass)
    
    @RequestMapping(path = Array("books/getForTitle"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get book with title")
    def getBookInfo(@RequestParam(name = "title") title: String): BookView = bookPersistanceService.getBookWithTitle(title).orNull

    @RequestMapping(path = Array("books/getForAuthor"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get books of author")
    def getBooksForAuthor(@RequestParam(name = "author name") name: String): List[BookView] = {
        bookPersistanceService.getAllBooksOfAuthor(name).map(_.toList).orNull
    }
}
