package com.inkwell.archives;


import com.inkwell.archives.model.BookEntity;
import com.inkwell.archives.model.DataSourceEntity;
import com.inkwell.archives.repository.BookRepository;
import com.inkwell.archives.repository.DataSourceRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class inkwellArchivesApp implements CommandLineRunner {

  @Autowired
  private DataSourceRepository dataSourceRepository;

  @Autowired
  private BookRepository bookRepository;

  public static void main(String[] args) {
    SpringApplication.run(inkwellArchivesApp.class, args);
    System.out.println("Application is running!");
  }

  @Override
  public void run(String... args) throws Exception {
    scrapeBooks();
  }

  private void scrapeBooks() {
    String urlHistoricalFiction =
            "https://books.toscrape.com/catalogue/category/books/historical-fiction_4/index.html";
    String urlTravel =
            "https://books.toscrape.com/catalogue/category/books/travel_2/index.html";
    String urlMysteryBook =
            "https://books.toscrape.com/catalogue/category/books/mystery_3/index.html";

    String urlScienceBook =
            "https://books.toscrape.com/catalogue/category/books/science_22/index.html";

    String urlMusicBook =
            "https://books.toscrape.com/catalogue/category/books/music_14/index.html";

    // Get mystery books
    try {
      Document document = Jsoup.connect(urlMusicBook).get();
      Elements bookCategory = document.select("h1");
      Elements elements = document.select(".product_pod");
      DataSourceEntity sourceEntity = dataSourceRepository.findById(1).orElse(null);
      for (Element bk : elements) {

        BookEntity scrappedBook = new BookEntity();

        // Book price
        String scrappedPrice = bk.select(".price_color").text();
        String price = scrappedPrice.replace("£", "");

        scrappedBook.setBookTitle(bk.select("h3 > a").text());
        scrappedBook.setBookPrice(Float.parseFloat(price));
        scrappedBook.setBookStock(19); // Placeholder, adjust as needed
        scrappedBook.setUpcCode("mystery90fa6122"); // Replace with dynamic UPC generator if available
        scrappedBook.setBookCategory(bookCategory.text());
        // Set or parse category as needed
        scrappedBook.setDataSource(sourceEntity);

       // bookRepository.save(scrappedBook);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
