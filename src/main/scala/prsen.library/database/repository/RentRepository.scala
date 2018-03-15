package prsen.library.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import prsen.library.model.rent.RentView

@Component
trait RentRepository extends CrudRepository[RentView, Int];
