package prsen.library.api

import io.swagger.annotations.{Api, ApiOperation}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import prsen.library.model.reader.ReaderView
import prsen.library.services.ReadersService

@RestController
@Api(value = "Library Management API")
@RequestMapping(path = Array("/api/library"))
class ReadersAPI(readersService: ReadersService) {
    
    private val log : Logger = LoggerFactory.getLogger(getClass)
    
    @RequestMapping(path = Array("readers/GET/getForName"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get readers with name")
    def getReaderInfoForSurname(@RequestParam(name = "reader name") name: String): java.util.ArrayList[ReaderView] = readersService.getReaderInfoForSurname(name)
    
    @RequestMapping(path = Array("readers/GET/getForId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get reader with id")
    def getReaderInfoForId(@RequestParam(name = "id") id: Int): ReaderView = readersService.getReaderInfoForId(id)
    
    @RequestMapping(path = Array("readers/PUT/addReader"), method = Array(RequestMethod.PUT))
    @ApiOperation(value = "Add reader to database")
    def addReader(@RequestParam(name = "name") name: String): Boolean = readersService.addReader(name)
    
    @RequestMapping(path = Array("readers/DELETE/deleteReader"), method = Array(RequestMethod.DELETE))
    @ApiOperation(value = "Remove reader from database")
    def deleteReaderForId(@RequestParam(name = "id") id: Int): Boolean = readersService.deleteReaderForId(id)
}