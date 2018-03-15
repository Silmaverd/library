package prsen.library.database.jdbc

import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import prsen.library.database.Credentials

class JdbcService {
    
    protected val log = LoggerFactory.getLogger(getClass)
    
    protected def createConnection(credentials: Credentials): Option[JdbcTemplate] = {
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
    
    protected var jdbcTemplate : JdbcTemplate = _
    
    protected def doWithJdbcTemplate[T](request: () => Option[T])(implicit credentials: Credentials): Option[T] = {
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
}
