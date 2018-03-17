package prsen.library.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import prsen.library.model.reader.ReaderView

@Component
trait ReaderRepository extends CrudRepository[ReaderView, Int]{
    
    def findByName(name: String) : java.util.ArrayList[ReaderView]
    
}
