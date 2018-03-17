package prsen.library.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import prsen.library.model.book.BookView

@Component
trait BookRepository extends CrudRepository[BookView, Int]{
    
    def findByTitle(title: String ) : BookView
    
    def findByAuthor(author: String) : java.util.ArrayList[BookView]
    
    def findByIsRented(boolean: Boolean) : java.util.ArrayList[BookView]
}
