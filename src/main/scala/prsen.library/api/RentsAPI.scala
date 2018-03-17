package prsen.library.api

import java.sql.Date

import io.swagger.annotations.{Api, ApiOperation}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import prsen.library.database.repository.{BookRepository, ReaderRepository, RentRepository}
import prsen.library.model.rent.RentView

@RestController
@Api(value = "Library Management API")
@RequestMapping(path = Array("/api/library"))
class RentsAPI(rentRepository: RentRepository,
               readerRepository: ReaderRepository,
               bookRepository: BookRepository) {
    
    @RequestMapping(path = Array("rents/getForId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent with id")
    def getRentInfoForId(@RequestParam(name = "id") id: Int): RentView = rentRepository.findById(id).orElse(null)
    
    @RequestMapping(path = Array("rents/getForReader"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent for reader with id")
    def getRentInfoForReader(@RequestParam(name = "id") id: Int): java.util.ArrayList[RentView] = rentRepository.findByReaderId(id)
    
    @RequestMapping(path = Array("rents/getForBooks"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent of book with id")
    def getRentInfoForBook(@RequestParam(name = "id") id: Int): java.util.ArrayList[RentView] = rentRepository.findByBookId(id)
    
    @RequestMapping(path = Array("rents/getForDate"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent with date")
    def getRentInfoForDate(@RequestParam(name = "date") date: Date): java.util.ArrayList[RentView] = rentRepository.findByRentalDate(date)
}
