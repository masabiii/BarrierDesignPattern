/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barrierdesignpatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 *
 * @author mhady
 */
public class Library {
    private List<Member> members = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private CyclicBarrier borrowBarrier = new CyclicBarrier(2); // Barrier untuk peminjaman
    private CyclicBarrier returnBarrier = new CyclicBarrier(2); // Barrier untuk pengembalian

    // Fungsi CRUD untuk anggota
    public void addMember(String name, boolean isMember) {
        members.add(new Member(name, isMember));
        System.out.println("Member added: " + name);
    }

    public void removeMember(String name) {
        members.removeIf(member -> member.getName().equals(name));
        System.out.println("Member removed: " + name);
    }

    // Fungsi CRUD untuk buku
    public void addBook(String title) {
        books.add(new Book(title));
        System.out.println("Book added: " + title);
    }

    public void removeBook(String title) {
        books.removeIf(book -> book.getTitle().equals(title));
        System.out.println("Book removed: " + title);
    }

    // Fungsi untuk meminjam buku
    public void borrowBook(String bookTitle, String memberName) {
        Thread borrowThread = new Thread(() -> {
            boolean isMember = members.stream().anyMatch(member -> member.getName().equals(memberName));
            Book book = findBookByTitle(bookTitle);
            if (book != null) {
                System.out.println(memberName + " is borrowing the book: " + bookTitle);
                try {
                    borrowBarrier.await(); // Menunggu sampai semua thread selesai meminjam
                    Thread.sleep(isMember ? 3000 : 2000); // Anggota meminjam lebih lama
                    System.out.println(memberName + " has returned the book: " + bookTitle);
                    returnBarrier.await(); // Menunggu sampai semua thread selesai mengembalikan
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Book not found: " + bookTitle);
            }
        });

        borrowThread.start();
    }

    private Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void shutdown() {
        // ...
    }
}
