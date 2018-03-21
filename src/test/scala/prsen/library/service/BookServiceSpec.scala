package scala.prsen.library.service

import lombok.NoArgsConstructor
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FreeSpec, Matchers}
import org.springframework.boot.test.context.SpringBootTest
import prsen.library.model.book.BookView
import prsen.library.services.BookService

import scala.prsen.library.repository.TestableRepository

@RunWith(classOf[JUnitRunner])
@NoArgsConstructor
@SpringBootTest
class BookServiceSpec extends FreeSpec with Matchers {

    "given a book service and a book" - {
        val testableRepository: TestableRepository = Mockito.mock(classOf[TestableRepository])
        val bookService: BookService = new BookService(testableRepository)
        val testBook = new BookView("1", "1", 1)

        "in case of error while fetching a book" - {
            Mockito.when(testableRepository.findByTitle(testBook.title)).thenThrow(new RuntimeException)

            "add book request returns false" in {
                bookService.addBook(testBook.title, testBook.author, testBook.amountInStock) shouldBe false
            }

            "drop book request returns false" in {
                bookService.dropBook(testBook.title, 1) shouldBe false
            }
        }

    }
}
