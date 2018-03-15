package prsen.library

import io.swagger.annotations.{Api, ApiOperation}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import prsen.library.database.RentPersistanceService
import prsen.library.model.rent.RentView

@RestController
@Api(value = "Library Management API")
@RequestMapping(path = Array("/api/library"))
class RentsAPI(rentPersistanceService: RentPersistanceService) {

    @RequestMapping(path = Array("rents/getForId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get rent with id")
    def getRentInfo(@RequestParam(name = "id") id: Int): RentView = rentPersistanceService.getRentWithId(id).orNull

    @RequestMapping(path = Array("rents/rent"), method = Array(RequestMethod.POST))
    @ApiOperation(value = "Rent a book to a reader")
    def rentBookToReader(@RequestParam(name = "name") name: String) : Unit = {

    }
}
