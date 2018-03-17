package prsen.library.api

import java.sql.Date

import io.swagger.annotations.{Api, ApiOperation}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.transaction.annotation.{Propagation, Transactional}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import prsen.library.database.repository.{BookRepository, ReaderRepository, RentRepository}
import prsen.library.model.rent.RentView

@RestController
@Api(value = "Library Management API")
@RequestMapping(path = Array("/api/library"))
class RentsAPI(rentRepository: RentRepository,
               readerRepository: ReaderRepository,
               bookRepository: BookRepository) {
    
    private val log : Logger = LoggerFactory.getLogger(getClass)
    
    @RequestMapping(path = Array("rents/GET/getForId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent with id")
    def getRentInfoForId(@RequestParam(name = "id") id: Int): RentView = rentRepository.findById(id).orElse(null)
    
    @RequestMapping(path = Array("rents/GET/getAllForReaderId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent for reader with id")
    def getAllRentInfoForReaderId(@RequestParam(name = "id") id: Int): java.util.ArrayList[RentView] = rentRepository.findByReaderId(id)
    
    @RequestMapping(path = Array("rents/GET/getOpenForReaderId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent for reader with id")
    def getOpenRentInfoForReaderId(@RequestParam(name = "id") id: Int): java.util.ArrayList[RentView] = rentRepository.findByReaderIdAndIsClosed(id, false)
    
    @RequestMapping(path = Array("rents/GET/getForBooks"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent of book with id")
    def getRentInfoForBook(@RequestParam(name = "id") id: Int): RentView = rentRepository.findByBookId(id)
    
    @RequestMapping(path = Array("rents/GET/getForDate"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent with date")
    def getRentInfoForDate(@RequestParam(name = "date") date: Date): java.util.ArrayList[RentView] = rentRepository.findByRentalDate(date)
    
    @RequestMapping(path = Array("rents/POST/returnBook"), method = Array(RequestMethod.POST))
    @ApiOperation(value = "Return a book")
    @Transactional(propagation = Propagation.REQUIRED)
    def returnBookAndCloseRent(@RequestParam(name = "title") title: String): Boolean = {
        try{
            val book = bookRepository.findByTitle(title)
            val rent = rentRepository.findByBookId(book.id)
            val reader = readerRepository.findById(rent.readerId).orElse(null)
            readerRepository.decreaseRentNumberById(reader.id)
            rentRepository.setClosedFor(true, rent.id)
            bookRepository.setIsRentedForId(book.id, false)
            true
        } catch {
            case t: Throwable => {
                log.error("Exception " + t.getMessage)
                // TODO: precise log
                false
            }
        }
    }
    
    @RequestMapping(path = Array("rents/POST/rentBook"), method = Array(RequestMethod.POST))
    @ApiOperation(value = "Rent a book to a reader")
    @Transactional(propagation = Propagation.REQUIRED)
    def rentBookToReader(@RequestParam(name = "reader id") readerId: Int,
                         @RequestParam(name = "book title") bookTitle: String): Boolean = {
        try {
            val reader = readerRepository.findById(readerId)
            val book = bookRepository.findByTitle(bookTitle)
            rentRepository.save(new RentView(reader.orElseGet(null).id, book.id, new Date(new java.util.Date().getTime), false))
            bookRepository.setIsRentedForId(book.id, true)
            readerRepository.increaseRentNumberById(reader.orElseGet(null).id)
            true
        } catch {
            case t: Throwable => {
                log.error("Exception " + t.getMessage)
                // TODO: precise log
                false
            }
        }
    }
    
    //TODO: implement error messages and response codes
}
