package prsen.library

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@ApiModel(description = "Operation response")
case class Response(@ApiModelProperty(required = true, value = "is ok") ok: Boolean,
                    @ApiModelProperty(value = "error code") errorCode: Option[Int],
                    @ApiModelProperty(value = "error message") errorMessage: Option[String])

@SpringBootApplication
class WebServer
    
    
object WebServer extends App{
    
    SpringApplication.run(classOf[WebServer], args: _*)
    
    override def main(args: Array[String]): Unit = {
        
        super.main(args)
        val persistanceAPI = new PersistenceAPI
    }
}
