package prsen.library.database.repository

import org.springframework.data.jpa.repository.{Modifying, Query}
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import prsen.library.model.book.BookView

@Component
trait BookRepository extends CrudRepository[BookView, Int]{
    
    def findByTitle(title: String) : BookView
    
    def findByAuthor(author: String) : java.util.ArrayList[BookView]
    
    @Modifying
    @Query("UPDATE BookView book SET book.amountInStock = (SELECT amountInStock FROM BookView WHERE id = ?1)-1 WHERE book.id = ?1")
    def decreaseAmountInStockByOneForId(id: Int) : Int
    
    @Modifying
    @Query("UPDATE BookView book SET book.amountInStock = (SELECT amountInStock FROM BookView WHERE id = ?1)+1 WHERE book.id = ?1")
    def increaseAmountInStockByOneForId(id: Int) : Int
    
    @Modifying
    @Query("UPDATE BookView book SET book.amountInStock = (SELECT amountInStock FROM BookView WHERE id = ?1)-?2 WHERE book.id = ?1")
    def dropAmountForId(id: Int, amount: Int) : Int
    
    @Modifying
    @Query("UPDATE BookView book SET book.amountInStock = (SELECT amountInStock FROM BookView WHERE id = ?1)+?2 WHERE book.id = ?1")
    def increaseAmountForId(id: Int, amount: Int) : Int
}
