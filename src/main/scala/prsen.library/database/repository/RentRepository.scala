package prsen.library.database.repository

import java.sql.Date

import org.springframework.data.jpa.repository.{JpaRepository, Modifying, Query}
import org.springframework.stereotype.Component
import prsen.library.model.rent.RentView

@Component
trait RentRepository extends JpaRepository[RentView, Int]{
    
    def findByRentalDate(date: Date) : java.util.ArrayList[RentView]
    
    def findByBookId(id: Int) : RentView
    
    def findByReaderId(id: Int) : java.util.ArrayList[RentView]
    
    def findByIsClosed(isClosed: Boolean) : java.util.ArrayList[RentView]
    
    def findByReaderIdAndIsClosed(id: Int, isClosed: Boolean) : java.util.ArrayList[RentView]
    
    @Modifying
    @Query("UPDATE RentView rent SET rent.isClosed = ?1 WHERE rent.id = ?2")
    def setClosedFor(isClosed : Boolean, rentId: Int): Int
}
