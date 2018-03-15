package prsen.library.database

import org.springframework.stereotype.Component
import prsen.library.model.reader.{ReaderMapper, ReaderView}

@Component
class ReaderPersistanceService(credentials: Credentials) extends JdbcService {
    def getReaderWithLastName(lastName: String): Option[ReaderView] = {
        doWithJdbcTemplate(() => {
            val query: String = s"select * from Reader where lastName = '$lastName'"
            val reader: ReaderView = jdbcTemplate.queryForObject(query, new ReaderMapper)
            Some(reader)
        })(credentials)
    }
}
