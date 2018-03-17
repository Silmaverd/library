package prsen.library.services

import org.springframework.stereotype.Component
import prsen.library.database.repository.BookRepository
import prsen.library.model.book.BookView

@Component
class BookService(bookRepository: BookRepository) {
    
    def getBookInfo(title: String): BookView = bookRepository.findByTitle(title)
    
    def getBooksForAuthor(name: String): java.util.ArrayList[BookView] = bookRepository.findByAuthor(name)
    
    def getBooksForId(id: Int): BookView = bookRepository.findById(id).orElse(null)
    
    def addBook(title: String,
                author: String,
                amount: Int): Boolean = {
        try {
            val book = bookRepository.findByTitle(title)
            if (book == null)
                bookRepository.save(new BookView(title, author, amount))
            else
                bookRepository.increaseAmountForId(book.id, amount)
            true
        } catch {
            case t: Throwable => false
        }
        
    }
    
    def dropBook(title: String,
                 amount: Int): Boolean = {
        try {
            val book = bookRepository.findByTitle(title)
            bookRepository.dropAmountForId(book.id, amount)
            // TODO: if amount reaches 0, DB should remove it, then remove from memory all related rents
            true
        } catch {
            case t: Throwable => false
        }
    }
}
