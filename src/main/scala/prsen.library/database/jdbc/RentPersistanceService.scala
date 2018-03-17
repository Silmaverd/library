package prsen.library.database.jdbc

import java.util.UUID

import org.springframework.stereotype.Component
import prsen.library.model.rent.{RentMapper, RentView}

@Component
@Deprecated
class RentPersistanceService(credentials: Credentials) extends JdbcService {

    def getRentWithId(id: Int): Option[RentView] = {
        try {
            doWithJdbcTemplate(() => {
                val query: String = s"select * from Rent where id = '$id'"
                val rent: RentView = jdbcTemplate.queryForObject(query, new RentMapper)
                Some(rent)
            })(credentials)
        } catch {
            case t: Throwable => None
        }
    }

    def rent(bookISBN: String, readerGuid: UUID): Boolean = {
        true
    }
}
