package prsen.library

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives
import io.swagger.annotations._
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation._
import prsen.library.model.BookView
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

@RestController
@RequestMapping(path = Array("/api/library"))
@Api(value = "Library Managment API", produces = MediaType.APPLICATION_JSON_VALUE)
class PersistenceAPI extends Directives with SprayJsonSupport with DefaultJsonProtocol {
    
    private val log = LoggerFactory.getLogger(getClass)
    
    @ApiModel
    case class AddBookToCacheRequest(@ApiModelProperty(required = true) username : String,
                                     @ApiModelProperty(required = true) processId : String)
    
    implicit private val addBookToCacheRequestFormat: RootJsonFormat[AddBookToCacheRequest] = jsonFormat2(AddBookToCacheRequest)
    
    @RequestMapping(path = Array("{book}"), method = Array(RequestMethod.GET))
    @ApiOperation(value = "Show book", response = classOf[BookView])
    @ApiResponses(value = Array(
        new ApiResponse(code = 200, message = "ok"),
        new ApiResponse(code = 400, message = "an error occured")
    ))
    def getBookInfo(@RequestParam(name = "title") title: String): BookView ={
        println("Witam serdecznie")
        new BookView("1", "sd", "sa")
    }
    
    /*@RequestMapping(path = Array("{book}"), method = Array(RequestMethod.PUT))
    @ApiOperation(value = "Add book", httpMethod = "PUT", response = classOf[Response])
    def addBookToCache(@PathVariable(name = "request") request : AddBookToCacheRequest): Response ={
        println("Witam serdecznie")
        Response(ok = true)
    }*/
}
