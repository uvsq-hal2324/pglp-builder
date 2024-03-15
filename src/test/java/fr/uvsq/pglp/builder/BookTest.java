package fr.uvsq.pglp.builder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {
  private static final String DUNE = "Dune";
  private static final String HERBERT = "Franck Herbert";
  private static final int YEAR = 1965;
  private static final Chapter CHAPTER1 = new Chapter("Chap. 1", "Durant la semaine qui précéda le départ pour Arrakis, ...");
  private static final Chapter CHAPTER2 = new Chapter("Chap. 2", "C'était un monde, ...");

  private Book dune;

  @BeforeEach
  public void setup() {
    dune = new Book.Builder(DUNE, CHAPTER1)
            .author(HERBERT)
            .year(YEAR)
            .addChapter(CHAPTER2)
            .build();
  }

  @Test
  public void shouldCreateASimpleBook() {
    Book b = new Book.Builder(DUNE, CHAPTER1)
            .build();

    assertEquals(DUNE, b.getTitle());
    assertEquals(Book.Builder.AUTHOR_DEFAULT, b.getAuthor());
    assertEquals(Book.Builder.YEAR_DEFAULT, b.getYear());
    assertEquals(CHAPTER1.title(), b.getChapter(1).title());
  }

  @Test
  public void shouldCreateABookWithYearAndAuthor() {
    Book b = new Book.Builder(DUNE, CHAPTER1)
            .author(HERBERT)
            .year(YEAR)
            .build();

    assertEquals(DUNE, b.getTitle());
    assertEquals(HERBERT, b.getAuthor());
    assertEquals(YEAR, b.getYear());
    assertEquals(CHAPTER1.title(), b.getChapter(1).title());
  }

  @Test
  public void shouldCreateABookWithChapters() {
    assertEquals(DUNE, dune.getTitle());
    assertEquals(HERBERT, dune.getAuthor());
    assertEquals(YEAR, dune.getYear());
    assertEquals(CHAPTER1.title(), dune.getChapter(1).title());
    assertEquals(CHAPTER2.title(), dune.getChapter(2).title());
  }

  @Test
  public void shouldReturnADescription() throws NoSuchMethodException {
    assertEquals(dune.toString(), "Dune, Franck Herbert, 1965 (2 chapters)");
  }
}
