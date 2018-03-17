package prsen.library.services

import org.slf4j.{Logger, LoggerFactory}
import org.springframework.stereotype.Component
import prsen.library.database.repository.ReaderRepository
import prsen.library.model.reader.ReaderView

@Component
class ReadersService(readerRepository: ReaderRepository) {
    
    private val log : Logger = LoggerFactory.getLogger(getClass)
    
    def getReaderInfoForSurname(name: String): java.util.ArrayList[ReaderView] = readerRepository.findByName(name)
    
    def getReaderInfoForId(id: Int): ReaderView = readerRepository.findById(id).orElse(null)
    
    def addReader(name: String): Boolean = {
        try {
            readerRepository.save(new ReaderView(name, 0))
            true
        } catch {
            case t: Throwable => {
                log.error("Cannot create reader " + t.getMessage)
                false
            }
        }
    }
    
    def deleteReaderForId(id: Int): Boolean = {
        try {
            val reader = readerRepository.findById(id).get()
            if (reader.rentsNumber > 0){
                log.warn("Cannot remove a reader with active rents")
                false
            } else {
                readerRepository.delete(reader)
                true
            }
        } catch {
            case t: Throwable => {
                log.error("No reader found with given id")
                false
            }
        }
    }
}
