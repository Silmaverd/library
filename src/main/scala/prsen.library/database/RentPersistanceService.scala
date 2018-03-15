package prsen.library.database

import org.springframework.stereotype.Component
import prsen.library.model.rent.{RentMapper, RentView}

@Component
class RentPersistanceService(credentials: Credentials) extends JdbcService {
    def getRentWithId(id: Int): Option[RentView] = {
        doWithJdbcTemplate(() => {
            val query: String = s"select * from Rent where id = '$id'"
            val rent: RentView = jdbcTemplate.queryForObject(query, new RentMapper)
            Some(rent)
        })(credentials)
    }
}
