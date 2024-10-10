// Charles "Nuke" Phillips
// Excercise 8d, p. 387

package ChapterNine.Excercise9_8;

import javax.swing.*;

public class BookArray
{
    public static void main(String[] args) 
    {
        Book[] books = new Book[10];
        String genres[] = new String[10];
        for(int i = 0; i < books.length; ++i)
        {
            String userEntry;
            // Robust input validation for checking whether a user typed "F" or N"
            while (true) {
                userEntry = JOptionPane.showInputDialog(null,
                "Please select the book genre\n" +
                "you would like to add: \n" +
                "F - Fiction\n" + 
                "N - NonFiction");

                if (userEntry == null || userEntry.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must enter a value.");
                    continue;  // Reprompt if input is empty or canceled
                }

                userEntry = userEntry.toLowerCase();
                // Using switch to handle valid and invalid input
                switch (userEntry) {
                    case "f" -> {
                        // Used to set the book title. I used this so the program is modular 
                        books[i] = new Fiction(setTitle());
                        genres[i] = "Fiction";
                        break;
                    }
                    case "n" -> {
                        // Used to set the book title. I used this so the program is modular 
                        books[i] = new NonFiction(setTitle());
                        genres[i] = "Non-Fiction";
                        break;
                    }
                    default -> JOptionPane.showMessageDialog(null, "Invalid selection! Please choose 'F' or 'N'.");
                }break;
            }
        }
        
        StringBuffer bookData = new StringBuffer();
        for(int i = 0; i < books.length; ++i)
        {
            bookData.append("\n#").append(i + 1).append(" ").append("\n");
            bookData.append("Title: ").append(books[i].getTitle()).append("\n");
            bookData.append("Genre: ").append(genres[i]).append("\n");
            bookData.append("Price: $ ").append(books[i].getPrice());
            
        }
        JOptionPane.showMessageDialog(null, 
        "Our available books include:\n" + bookData);
    }
    // Method to set the book title
    private static String setTitle() 
    {
        String title;
        while (true) {
            title = JOptionPane.showInputDialog(null, "Enter the book title:");
            // Check if the title is valid
            if (title != null && !title.trim().isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid title. Please enter a valid book title.");
            }
        }
        return title;  // Return valid title
    }
}
