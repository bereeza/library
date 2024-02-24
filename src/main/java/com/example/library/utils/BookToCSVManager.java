package com.example.library.utils;

import com.example.library.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@NoArgsConstructor
public final class BookToCSVManager {
    private final static String TYPE = "text/csv";
    public static void save(List<Book> data, HttpServletResponse response) {
        response.setContentType(TYPE);
        response.setHeader("Content-Disposition", "attachment; filename=books.csv");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("ID,Name,Genre\n");

            for (Book book : data) {
                String line = String.format("%d,%s,%s\n",
                        book.getId(),
                        book.getName(),
                        book.getGenre().toString());

                writer.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        }
    }
}
