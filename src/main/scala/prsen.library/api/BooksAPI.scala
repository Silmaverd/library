package prsen.library.api

import io.swagger.annotations._
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.{Propagation, Transactional}
import org.springframework.web.bind.annotation._
import prsen.library.model.book.BookView
import prsen.library.services.BookService

@RestController
@RequestMapping(path = Array("/api/library"))
@Api(value = "Library Managment API")
class BooksAPI(bookService: BookService) {
    
    private val log = LoggerFactory.getLogger(getClass)
    
    @RequestMapping(path = Array("books/GET/getForTitle"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get book with title")
    def getBookInfo(@RequestParam(name = "title") title: String): BookView = bookService.getBookInfo(title)
    
    @RequestMapping(path = Array("books/GET/getForAuthor"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get book with author")
    def getBooksForAuthor(@RequestParam(name = "name") name: String): java.util.ArrayList[BookView] = bookService.getBooksForAuthor(name)
    
    @RequestMapping(path = Array("books/GET/getForId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get book with id")
    def getBooksForAuthor(@RequestParam(name = "id") id: Int): BookView = bookService.getBooksForId(id)
    
    @RequestMapping(path = Array("books/PUT/addBook"), method = Array(RequestMethod.PUT))
    @ApiOperation(value = "Add book to the database")
    @Transactional(propagation = Propagation.REQUIRED)
    def addBook(@RequestParam(name = "title") title: String,
                @RequestParam(name = "author") author: String,
                @RequestParam(name = "amount") amount: Int): Boolean = bookService.addBook(title, author, amount)
    
    @RequestMapping(path = Array("books/DELETE/dropForTitle"), method = Array(RequestMethod.DELETE))
    @ApiOperation(value = "Drop a book from database")
    @Transactional(propagation = Propagation.REQUIRED)
    def dropBook(@RequestParam(name = "title") title: String,
                 @RequestParam(name = "amount") amount: Int): Boolean = bookService.dropBook(title, amount)
    
}
