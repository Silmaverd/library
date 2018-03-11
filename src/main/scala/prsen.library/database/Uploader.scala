package prsen.library.database

import java.sql.{Connection, DriverManager}
import javax.sql.DataSource

import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import prsen.library.model.{BookMapper, BookView, ReaderMapper, ReaderView}

class Uploader {
    
    private val log = LoggerFactory.getLogger(getClass)
    
    private def createConnection(credentials: Credentials): Option[JdbcTemplate] = {
        try {
            log.info("Opening database connection")
            Class.forName(credentials.driver).newInstance()
            val dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource(
                credentials.url,
                credentials.user,
                credentials.password)
            Some(new JdbcTemplate(dataSource))
        } catch {
            case t: Throwable =>
                log.error("Connecting to database failed" + t.getMessage.toString)
                None
        }
        
    }
    
    private var jdbcTemplate : JdbcTemplate = _
    
    private def doWithJdbcTemplate[T](request: () => Option[T])(implicit credentials: Credentials): Option[T] = {
        createConnection(credentials) match {
            case Some(jdbc) =>
                log.info("Connecting succesful")
                jdbcTemplate = jdbc
                request.apply()
            case None =>
                log.info("Aborting")
                None
        }
    }
    
    def getBookWithTitle(title: String)(implicit credentials: Credentials): Option[BookView] = {
        doWithJdbcTemplate(() => {
            val query: String = s"select * from Book where title = '$title'"
            val book: BookView = jdbcTemplate.queryForObject(query, new BookMapper)
            Some(book)
        })(credentials)
    }
    
    def getReaderWithLastName(lastName: String)(implicit credentials: Credentials): Option[ReaderView] = {
        doWithJdbcTemplate(() => {
            val query: String = s"select * from Reader where lastName = '$lastName'"
            val reader: ReaderView = jdbcTemplate.queryForObject(query, new ReaderMapper)
            Some(reader)
        })(credentials)
    }
}
