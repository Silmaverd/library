package prsen.library.database.repository

import org.springframework.data.jpa.repository.{JpaRepository, Modifying, Query}
import org.springframework.stereotype.Component
import prsen.library.model.reader.ReaderView

@Component
trait ReaderRepository extends JpaRepository[ReaderView, Int]{
    
    def findByName(name: String) : java.util.ArrayList[ReaderView]
    
    @Modifying
    @Query("UPDATE ReaderView reader SET reader.rentsNumber = (SELECT rentsNumber FROM ReaderView WHERE id = ?1)-1 WHERE reader.id = ?1")
    def decreaseRentNumberById(id: Int) : Int
    
    @Modifying
    @Query("UPDATE ReaderView reader SET reader.rentsNumber = (SELECT rentsNumber FROM ReaderView WHERE id = ?1)+1 WHERE reader.id = ?1")
    def increaseRentNumberById(id: Int) : Int
    
    
}
