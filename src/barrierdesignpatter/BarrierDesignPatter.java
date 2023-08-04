/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package barrierdesignpatter;

/**
 *
 * @author mhady
 */
public class BarrierDesignPatter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           Library library = new Library();

        // Adding members and books
        library.addMember("Abi", true);
        library.addMember("Alice", false);

        library.addBook("Java Programming");
        library.addBook("Design Patterns");

        // Borrowing books
        Thread borrowThread1 = new Thread(() -> {
            library.borrowBook("Java Programming", "Abi");
        });

        Thread borrowThread2 = new Thread(() -> {
            library.borrowBook("Design Patterns", "Alice");
        });

        borrowThread1.start();
        borrowThread2.start();

        try {
            borrowThread1.join();
            borrowThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Removing members and books
        library.removeMember("Abi");
        library.removeBook("Java Programming");

        library.shutdown();
    }
    
}
