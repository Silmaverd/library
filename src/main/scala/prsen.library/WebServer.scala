package prsen.library

import io.swagger.annotations.{ApiModel, ApiModelProperty}
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
    def initializeRents(rentsRepo: RentRepository,
                        booksRepo: BookRepository,
                        readersRepo: ReaderRepository): Boolean = {
        try{
            val reader = readersRepo.findByName("Roman Stanisławski").get(0)
            val book1 = booksRepo.findByTitle("Achaja")
            val book2 = booksRepo.findByTitle("Pomnik Cesarzowej Achai")
            rentsRepo.save(new RentView(reader.id, book1.id, new java.sql.Date(2018, 3, 12), false))
            rentsRepo.save(new RentView(reader.id, book2.id, new java.sql.Date(2018, 2, 9), false))
            true
        } catch {
            case t: Throwable =>
                log.error("Failed to initialize readers repository")
                false
        }
    }
    
    @Bean
    def initializeBooks(repo: BookRepository): Boolean = {
        try {
            repo.save(new BookView("Virion", "Andrzej Ziemiański", 1))
            repo.save(new BookView("Gra o Tron", "George R. R. Martin", 1))
            repo.save(new BookView("Achaja", "Andrzej Ziemiański", 2))
            repo.save(new BookView("Pomnik Cesarzowej Achai", "Andrzej Ziemiański", 1))
            repo.save(new BookView("Siewca Wiatru", "Maja Lidia Kossakowska", 1))
            repo.save(new BookView("Kroniki Jakuba Wędrowycza", "Andrzej Pilipiuk", 4))
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
            repo.save(new ReaderView("Jan Kowalski",  0))
            repo.save(new ReaderView("Stanisław Janowski", 0))
            repo.save(new ReaderView("Roman Stanisławski", 2))
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
