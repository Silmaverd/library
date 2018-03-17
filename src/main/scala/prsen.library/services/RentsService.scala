package prsen.library.services

import java.sql.Date
import java.util.concurrent.TimeUnit

import org.slf4j.{Logger, LoggerFactory}
import org.springframework.stereotype.Component
import prsen.library.database.repository.{BookRepository, ReaderRepository, RentRepository}
import prsen.library.model.rent.RentView

@Component
class RentsService(rentRepository: RentRepository,
                   readerRepository: ReaderRepository,
                   bookRepository: BookRepository) {
    
    private val log: Logger = LoggerFactory.getLogger(getClass)
    
    def getRentInfoForId(id: Int): RentView = rentRepository.findById(id).orElse(null)
    
    def getAllRentInfoForReaderId(id: Int): java.util.ArrayList[RentView] = rentRepository.findByReaderId(id)
    
    def getOpenRentInfoForReaderId(id: Int): java.util.ArrayList[RentView] = rentRepository.findByReaderIdAndIsClosed(id, false)
    
    def getRentInfoForBook(id: Int): RentView = rentRepository.findByBookId(id)
    
    def getRentInfoForDate(date: Date): java.util.ArrayList[RentView] = rentRepository.findByRentalDate(date)
    
    def returnBookAndCloseRent(title: String,
                               damaged: Boolean): Int = {
        try {
            val book = bookRepository.findByTitle(title)
            val rent = rentRepository.findByBookId(book.id)
            if (!rent.isClosed) {
                val reader = readerRepository.findById(rent.readerId).orElse(null)
                val now = new Date(new java.util.Date().getTime)
                readerRepository.decreaseRentNumberById(reader.id)
                if (damaged) readerRepository.setDamagedById(reader.id, true)
                bookRepository.increaseAmountInStockByOneForId(book.id)
                val fee: Int = calculateFee(rent.rentalDate, now)
                rentRepository.setClosedFor(true, rent.id)
                rentRepository.setFeeFor(rent.id, fee)
                fee
            } else {
                log.warn("this book is not rented")
                0
            }
        } catch {
            case t: Throwable => {
                log.error("Exception " + t.getMessage)
                // TODO: precise log
                0
            }
        }
    }
    
    def notifyABookLoss(title: String): Int = {
        try {
            val book = bookRepository.findByTitle(title)
            val rent = rentRepository.findByBookId(book.id)
            val reader = readerRepository.findById(rent.readerId).orElse(null)
            val now = new Date(new java.util.Date().getTime)
            readerRepository.decreaseRentNumberById(reader.id)
            readerRepository.setLostById(reader.id, true)
            rentRepository.setClosedFor(true, rent.id)
            rentRepository.setClosingDateFor(rent.id, now)
            // Fee for lost book is 40 PLN
            val fee: Int = calculateFee(rent.rentalDate, now) + 40
            rentRepository.setFeeFor(rent.id, fee)
            fee
        } catch {
            case t: Throwable => {
                log.error("Exception " + t.getMessage)
                // TODO: precise log
                0
            }
        }
    }
    
    def rentBookToReader(readerId: Int,
                         bookTitle: String): Boolean = {
        try {
            val reader = readerRepository.findById(readerId)
            val book = bookRepository.findByTitle(bookTitle)
            if (book.amountInStock < 1) false
            else {
                rentRepository.save(new RentView(reader.orElseGet(null).id, book.id, new Date(new java.util.Date().getTime), false))
                bookRepository.decreaseAmountInStockByOneForId(book.id)
                readerRepository.increaseRentNumberById(reader.orElseGet(null).id)
                true
            }
        } catch {
            case t: Throwable => {
                log.error("Exception " + t.getMessage)
                // TODO: precise log
                false
            }
        }
    }
    
    private def calculateFee(startingDate: Date, closingDate: Date): Int = {
        // Assuming, that allowed rental time is 30 days and fee is equal to 1 PLN for each day above 30
        val utilStartDate = new java.util.Date(startingDate.getYear, startingDate.getMonth, startingDate.getDay)
        val utilEndDate = new java.util.Date(closingDate.getYear, closingDate.getMonth, closingDate.getDay)
        val diff = utilEndDate.getTime - utilStartDate.getTime
        val diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).asInstanceOf[Int]
        if (diffDays < 30) 0 else diffDays - 30
        
    }
}
