package prsen.library

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import prsen.library.database.repository.{BookRepository, ReaderRepository, RentRepository}
import prsen.library.model.book.BookView
import prsen.library.model.reader.ReaderView
import prsen.library.model.rent.RentView

@ApiModel(description = "Response payload")
trait Payload

@ApiModel(description = "Operation response")
case class Response(@ApiModelProperty(required = true, value = "is ok") ok: Boolean,
                    @ApiModelProperty(value = "error code") errorCode: Option[Int] = None,
                    @ApiModelProperty(value = "error message") errorMessage: Option[String] = None,
                    @ApiModelProperty(value = "payload") payload: Option[Payload] = None)
// TODO: Return this in all api responses

@SpringBootApplication
class WebServer

object WebServer extends App {
    
    SpringApplication.run(classOf[WebServer], args: _*)

    private val log : Logger = LoggerFactory.getLogger(getClass)

    // TODO: move initializing methods out of web server
    
    @Bean
    def initializeBooks(repo: BookRepository): Boolean = {
        try {
            repo.save(new BookView(1, "Virion", "Andrzej Ziemiański", false))
            repo.save(new BookView(2, "Gra o Tron", "George R. R. Martin", true))
            repo.save(new BookView(3, "Achaja", "Andrzej Ziemiański", true))
            repo.save(new BookView(4, "Pomnik Cesarzowej Achai", "Andrzej Ziemiański", false))
            repo.save(new BookView(5, "Siewca Wiatru", "Maja Lidia Kossakowska", false))
            repo.save(new BookView(6, "Kroniki Jakuba Wędrowycza", "Andrzej Pilipiuk", false))
            true
        } catch {
            case t: Throwable =>
                log.error("Failed to initialize books repository")
                false
        }
    }
    
    @Bean
    def initializeReaders(repo: ReaderRepository): Boolean = {
        try{
            repo.save(new ReaderView("Jan Kowalski",  0, 1))
            repo.save(new ReaderView("Stanisław Janowski", 0, 2))
            repo.save(new ReaderView("Roman Stanisławski", 2, 3))
            true
        } catch {
            case t: Throwable =>
                log.error("Failed to initialize readers repository")
                false
        }
    }

    @Bean
    def initializeRents(repo: RentRepository): Boolean = {
        try{
            repo.save(new RentView(1, 3, 1, new java.sql.Date(2018, 3, 12), false))
            repo.save(new RentView(2, 3, 2, new java.sql.Date(2018, 2, 9), false))
            true
        } catch {
            case t: Throwable =>
                log.error("Failed to initialize readers repository")
                false
        }
    }

    
    
    override def main(args: Array[String]): Unit = {
        
        super.main(args)
        
        // TODO: write configuration container and get local ip from maven repository properties
        log.info("Serving at 127.0.0.1:8080/swagger-ui.html")
    }
}
