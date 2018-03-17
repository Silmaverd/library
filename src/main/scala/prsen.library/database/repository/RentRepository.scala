package prsen.library.database.repository

import java.sql.Date

import org.springframework.data.repository.CrudRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Component
import prsen.library.model.rent.RentView

@Component
trait RentRepository extends CrudRepository[RentView, Int]{
    
    def findByRentalDate(date: Date) : java.util.ArrayList[RentView]
    
    def findByBookId(id: Int) : java.util.ArrayList[RentView]
    
    def findByReaderId(id: Int) : java.util.ArrayList[RentView]
    
    def findByIsClosed(isClosed: Boolean) : java.util.ArrayList[RentView]
    
    @Modifying
    @Query("UPDATE RENT rent SET rent.closed = 'true'")
    def setRentIsClosedById(rentId: Int): Unit
}
