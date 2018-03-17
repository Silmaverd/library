package prsen.library.api

import io.swagger.annotations.{Api, ApiOperation}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import prsen.library.database.repository.ReaderRepository
import prsen.library.model.reader.ReaderView

@RestController
@Api(value = "Library Management API")
@RequestMapping(path = Array("/api/library"))
class ReadersAPI(readerRepository: ReaderRepository) {
    
    @RequestMapping(path = Array("readers/GET/getForName"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get readers with name")
    def getReaderInfoForSurname(@RequestParam(name = "reader name") name: String): java.util.ArrayList[ReaderView] = {
        readerRepository.findByName(name)
    }
    
    @RequestMapping(path = Array("readers/GET/getForId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get reader with id")
    def getReaderInfoForId(@RequestParam(name = "id") id : Int): ReaderView = readerRepository.findById(id).orElse(null)
    
}