package prsen.library.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import prsen.library.model.book.BookView

@Component
trait BookRepository extends CrudRepository[BookView, Int];
