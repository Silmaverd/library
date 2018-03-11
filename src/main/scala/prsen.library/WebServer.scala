package prsen.library

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import prsen.library.database.{Credentials, H2DatabaseCredentials, Uploader}
import prsen.library.model.{BookView, ReaderView}

@ApiModel(description = "Response payload")
trait Payload

@ApiModel(description = "Operation response")
case class Response(@ApiModelProperty(required = true, value = "is ok") ok: Boolean,
                    @ApiModelProperty(value = "error code") errorCode: Option[Int] = None,
                    @ApiModelProperty(value = "error message") errorMessage: Option[String] = None,
                    @ApiModelProperty(value = "payload") payload: Option[Payload] = None)

@SpringBootApplication
class WebServer


object WebServer extends App {
    
    SpringApplication.run(classOf[WebServer], args: _*)
    
    override def main(args: Array[String]): Unit = {
        
        super.main(args)
    
        val persistanceAPI = new PersistenceAPI
        
        val h2DatabaseCredentials : Credentials = H2DatabaseCredentials.get()
        
        val uploader = new Uploader
        
        val book : Option[BookView] = uploader.getBookWithTitle("Achaja")(credentials = h2DatabaseCredentials)
        
        book match {
            case Some(book) => println(book.title + " " + book.author + "   is rented?: "+book.isRented)
            case None => println("Nothing found")
        }
    
        val reader : Option[ReaderView] = uploader.getReaderWithLastName("Kowalski")(credentials = h2DatabaseCredentials)
    
        reader match {
            case Some(r) => println(r.firstName + " " + r.lastName)
            case None => println("Nothing found")
        }
    }
}
