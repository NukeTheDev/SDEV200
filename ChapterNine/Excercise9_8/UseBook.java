// Charles "Nuke" Phillips
// Excercise 8c, p. 387

package ChapterNine.Excercise9_8;

import javax.swing.*;

public class UseBook {
    public static void main(String[] args) {
        // String array for genres
        String[] genres = {"Fiction", "Non-Fiction"};
        
        // Variables to hold one book of each genre
        Fiction fictionBook = null;
        NonFiction nonFictionBook = null;

        for (String genre : genres) {
            // Display prompt
            while (true) {
                String title = JOptionPane.showInputDialog(null, 
                "Please enter the title of a " + genre + " book.");
                if (title == null || title.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Title cannot be empty!");
                    continue;
                }

                switch (genre) {
                    case "Fiction" -> fictionBook = new Fiction(title);
                    case "Non-Fiction" -> nonFictionBook = new NonFiction(title);
                    default -> {
                        JOptionPane.showMessageDialog(null, "Invalid genre! Please choose 'Fiction' or 'Non-Fiction'.");
                        continue;
                    }
                }
                break;
            }
        }

        // Prepare the book data for display
        StringBuilder bookData = new StringBuilder();

        if (fictionBook != null) {
            bookData.append("\n# 1\n");
            bookData.append("Title: ").append(fictionBook.getTitle()).append("\n");
            bookData.append("Genre: Fiction\n");
            bookData.append("Price: $ ").append(fictionBook.getPrice()).append("\n");
        }

        if (nonFictionBook != null) {
            bookData.append("\n# 2\n");
            bookData.append("Title: ").append(nonFictionBook.getTitle()).append("\n");
            bookData.append("Genre: Non-Fiction\n");
            bookData.append("Price: $ ").append(nonFictionBook.getPrice()).append("\n");
        }

        JOptionPane.showMessageDialog(null, 
        "Our available books include:\n" + bookData);
    }
}
