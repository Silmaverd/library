package prsen.library.api

import io.swagger.annotations.{Api, ApiOperation}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import prsen.library.database.repository.ReaderRepository
import prsen.library.model.reader.ReaderView

@RestController
@Api(value = "Library Management API")
@RequestMapping(path = Array("/api/library"))
class ReadersAPI(readerRepository: ReaderRepository) {
    
    private val log : Logger = LoggerFactory.getLogger(getClass)
    
    @RequestMapping(path = Array("readers/GET/getForName"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get readers with name")
    def getReaderInfoForSurname(@RequestParam(name = "reader name") name: String): java.util.ArrayList[ReaderView] = {
        readerRepository.findByName(name)
    }
    
    @RequestMapping(path = Array("readers/GET/getForId"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Get reader with id")
    def getReaderInfoForId(@RequestParam(name = "id") id: Int): ReaderView = readerRepository.findById(id).orElse(null)
    
    @RequestMapping(path = Array("readers/PUT/addReader"), method = Array(RequestMethod.PUT))
    @ApiOperation(value = "Add reader to database")
    def addReader(@RequestParam(name = "name") name: String): Boolean = {
        try {
            readerRepository.save(new ReaderView(name, 0))
            true
        } catch {
            case t: Throwable => {
                log.error("Cannot create reader " + t.getMessage)
                false
            }
        }
    }
    
    @RequestMapping(path = Array("readers/DELETE/deleteReader"), method = Array(RequestMethod.DELETE))
    @ApiOperation(value = "Remove reader from database")
    def deleteReaderForId(@RequestParam(name = "id") id: Int): Boolean = {
        try {
            val reader = readerRepository.findById(id).get()
            if (reader.rentsNumber > 0){
                log.warn("Cannot remove a reader with active rents")
                false
            } else {
                readerRepository.delete(reader)
                true
            }
        } catch {
            case t: Throwable => {
                log.error("No reader found with given id")
                false
            }
        }
    }
}