package prsen.library

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives
import io.swagger.annotations.{Api, ApiModel, ApiModelProperty, ApiOperation}
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RequestMethod, RestController}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

@RestController
@RequestMapping(path = Array("/api/library"))
@Api(value = "Library Managment API", produces = "application/json")
class PersistenceAPI extends Directives with SprayJsonSupport with DefaultJsonProtocol {
    
    private val log = LoggerFactory.getLogger(getClass)
    
    @ApiModel
    case class AddBookToCacheRequest(@ApiModelProperty(required = true) username : String,
                                     @ApiModelProperty(required = true) processId : String)
    
    implicit private val addBookToCacheRequestFormat: RootJsonFormat[AddBookToCacheRequest] = jsonFormat2(AddBookToCacheRequest)
    
    @RequestMapping(path = Array("{book}"), method = Array(RequestMethod.PUT))
    @ApiOperation(value = "Add book", httpMethod = "PUT", response = classOf[Response])
    def addBookToCache(@PathVariable(name = "request") request : AddBookToCacheRequest): Unit ={
    
    }
}
