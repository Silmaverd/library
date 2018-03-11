package prsen.library.database

import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import prsen.library.model.BookView

class JdbcExecutor {
    private val log = LoggerFactory.getLogger(getClass)
    
    private def createConnection()(implicit credentials: Credentials): Option[JdbcTemplate] = {
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
    
    private def doWithJdbcTemplate(request: () => Option[BookView])(implicit credentials: Credentials): Option[BookView] = {
        createConnection() match {
            case Some(jdbcTemplate) =>
                log.info("Connecting succesful")
                request.apply()
            case None =>
                log.info("Aborting")
                None
        }
    }
}
