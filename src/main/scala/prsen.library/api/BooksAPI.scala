package prsen.library.api

import io.swagger.annotations._
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation._
import prsen.library.database.repository.BookRepository
import prsen.library.model.book.BookView

@RestController
@RequestMapping(path = Array("/api/library"))
@Api(value = "Library Managment API")
class BooksAPI(bookRepository: BookRepository) {
    
    private val log = LoggerFactory.getLogger(getClass)
    
    @RequestMapping(path = Array("books/getForTitle"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get book with title")
    def getBookInfo(@RequestParam(name = "title") title: String): BookView = bookRepository.findById(1).orElse(null)
    
    @RequestMapping(path = Array("books/getForAuthor"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get book with author")
    def getBooksForAuthor(@RequestParam(name = "name") name: String): java.util.ArrayList[BookView] = {
        bookRepository.findByAuthor(name)
    }
    
    @RequestMapping(path = Array("books/getForRented"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rented books")
    def getBooksForAuthor(@RequestParam(name = "is rented") rented: Boolean): java.util.ArrayList[BookView] = {
        bookRepository.findByIsRented(rented)
    }
}
