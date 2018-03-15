package prsen.library.database

import org.springframework.stereotype.Component
import prsen.library.model.reader.{ReaderMapper, ReaderView}

@Component
class ReaderPersistanceService(credentials: Credentials) extends JdbcService {

    def getReaderWithName(name: String): Option[ReaderView] = {
        try {
            doWithJdbcTemplate(() => {
                val query: String = s"select * from Reader where readerName = '$name'"
                val reader: ReaderView = jdbcTemplate.queryForObject(query, new ReaderMapper)
                Some(reader)
            })(credentials)
        } catch {
            case t: Throwable => None
        }
    }
}
