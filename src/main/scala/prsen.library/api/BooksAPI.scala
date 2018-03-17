package prsen.library.api

import io.swagger.annotations._
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.{Propagation, Transactional}
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
    
    @RequestMapping(path = Array("books/PUT/addBook"), method = Array(RequestMethod.PUT))
    @ApiOperation(value = "Add book to the database")
    @Transactional(propagation = Propagation.REQUIRED)
    def addBook(@RequestParam(name = "title") title: String,
                @RequestParam(name = "author") author: String,
                @RequestParam(name = "amount") amount: Int): Boolean = {
        try {
            val book = bookRepository.findByTitle(title)
            if (book == null)
                bookRepository.save(new BookView(title, author, amount))
            else
                bookRepository.increaseAmountForId(book.id, amount)
            true
        } catch {
            case t: Throwable => false
        }
        
    }
    
    @RequestMapping(path = Array("books/DELETE/dropForTitle"), method = Array(RequestMethod.DELETE))
    @ApiOperation(value = "Drop a book from database")
    @Transactional(propagation = Propagation.REQUIRED)
    def dropBook(@RequestParam(name = "title") title: String,
                 @RequestParam(name = "amount") amount: Int): Boolean = {
        try {
            val book = bookRepository.findByTitle(title)
            bookRepository.dropAmountForId(book.id, amount)
            // TODO: if amount reaches 0, DB should remove it, then remove from memory all related rents
            true
        } catch {
            case t: Throwable => false
        }
        
    }
    
}
