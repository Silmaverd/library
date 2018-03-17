package prsen.library.api

import java.sql.Date

import io.swagger.annotations.{Api, ApiOperation}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.transaction.annotation.{Propagation, Transactional}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import prsen.library.model.rent.RentView
import prsen.library.services.RentsService

@RestController
@Api(value = "Library Management API")
@RequestMapping(path = Array("/api/library"))
class RentsAPI(rentsService: RentsService) {
    
    private val log: Logger = LoggerFactory.getLogger(getClass)
    
    @RequestMapping(path = Array("rents/GET/getForId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent with id")
    def getRentInfoForId(@RequestParam(name = "id") id: Int): RentView = rentsService.getRentInfoForId(id)
    
    @RequestMapping(path = Array("rents/GET/getAllForReaderId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent for reader with id")
    def getAllRentInfoForReaderId(@RequestParam(name = "id") id: Int): java.util.ArrayList[RentView] = rentsService.getAllRentInfoForReaderId(id)
    
    @RequestMapping(path = Array("rents/GET/getOpenForReaderId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent for reader with id")
    def getOpenRentInfoForReaderId(@RequestParam(name = "id") id: Int): java.util.ArrayList[RentView] = rentsService.getOpenRentInfoForReaderId(id)
    
    @RequestMapping(path = Array("rents/GET/getForBooks"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent of book with id")
    def getRentInfoForBook(@RequestParam(name = "id") id: Int): RentView = rentsService.getRentInfoForBook(id)
    
    @RequestMapping(path = Array("rents/GET/getForDate"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent with date")
    def getRentInfoForDate(@RequestParam(name = "date") date: Date): java.util.ArrayList[RentView] = rentsService.getRentInfoForDate(date)
    
    @RequestMapping(path = Array("rents/POST/returnBook"), method = Array(RequestMethod.POST))
    @ApiOperation(value = "Return a book")
    @Transactional(propagation = Propagation.REQUIRED)
    def returnBookAndCloseRent(@RequestParam(name = "title") title: String,
                               @RequestParam(name = "is damaged?") damaged: Boolean): Int = rentsService.returnBookAndCloseRent(title, damaged)
    
    @RequestMapping(path = Array("rents/DELETE/notifyLoss"), method = Array(RequestMethod.DELETE))
    @ApiOperation(value = "Notify a loss of book")
    @Transactional(propagation = Propagation.REQUIRED)
    def notifyABookLoss(@RequestParam(name = "title") title: String): Int = rentsService.notifyABookLoss(title)
    
    @RequestMapping(path = Array("rents/POST/rentBook"), method = Array(RequestMethod.POST))
    @ApiOperation(value = "Rent a book to a reader")
    @Transactional(propagation = Propagation.REQUIRED)
    def rentBookToReader(@RequestParam(name = "reader id") readerId: Int,
                         @RequestParam(name = "book title") bookTitle: String): Boolean = rentsService.rentBookToReader(readerId, bookTitle)
    
    
    //TODO: implement error messages and response codes
}
