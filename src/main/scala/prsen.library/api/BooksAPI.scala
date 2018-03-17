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
    
    @RequestMapping(path = Array("books/GET/getForTitle"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get book with title")
    def getBookInfo(@RequestParam(name = "title") title: String): BookView = bookRepository.findByTitle(title)
    
    @RequestMapping(path = Array("books/GET/getForAuthor"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get book with author")
    def getBooksForAuthor(@RequestParam(name = "name") name: String): java.util.ArrayList[BookView] = {
        bookRepository.findByAuthor(name)
    }
    
    @RequestMapping(path = Array("books/GET/getForId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get book with id")
    def getBooksForAuthor(@RequestParam(name = "id") id: Int): BookView = {
        bookRepository.findById(id).orElse(null)
    }
}
