package library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

public class dbconn {
    Connection conn;
    Statement stmt;
    Random rand = new Random();

    dbconn(){
        try{
            String s = "jdbc:mysql://localhost:3306/library";
            conn = DriverManager.getConnection(s,Your Login ID,Your Password);
            stmt = conn.createStatement();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    void closeconn(){
        try{
            conn.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    String login(String user, String pass){
        Boolean s = false;
        String role = "false";
        try{
            ResultSet rs = stmt.executeQuery("Select * from users where username='"+user+"';");
            while (rs.next()){
                if(!s){
                    s = pass.equals(rs.getString("pass"));
                    role = rs.getString("role");
                    universal.role = role;
                    universal.id = rs.getInt("userid");
                }else {
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return role;
    }

    void Borrow(int userid, String book){
        int borrowid = rand.nextInt(10000);
        LocalDate bd = LocalDate.now();
        int Bookid = -1;
        ResultSet rs;
        String one = "Select book_id from books where title='"+book+"';";
        String three;
        try{
            rs = stmt.executeQuery(one);
            if (rs.next()){
                Bookid = rs.getInt("book_id");
            }
            if(Bookid != -1){
                three = "Insert into borrow(borrowid,bookid,userid,borrow_date) values("+borrowid+","+Bookid+","+userid+",'"+bd+"')";
                stmt.executeUpdate(three);
            }
            System.out.println("Borrow added...");
        }catch (Exception e){
            System.out.println("Borrow Exception");
            System.out.println(e.getMessage());
        }
    }

    void Return(int userid, String book){
        LocalDate bd = LocalDate.now();
        int Bookid = -1;
        String one = "Select book_id from books where title='"+book+"';";
        String two ;
        ResultSet rs;
        try{
            rs = stmt.executeQuery(one);
            if(rs.next()){
                Bookid = rs.getInt("book_id");
            }
            if(Bookid != -1){
                two = "Update borrow set return_date='"+bd+"' where userid="+userid+" and bookid="+Bookid;
                stmt.executeUpdate(two);
            }
            System.out.println("Return added...");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    ObservableList pending(int userid){
        ObservableList<Map<String, Object>> ll = FXCollections.<Map<String, Object>>observableArrayList();
        String one = "select * from borrow,books where borrow.userid="+userid+" and borrow.return_date IS NULL and books.book_id=borrow.bookid";
        try{
            ResultSet s = stmt.executeQuery(one);
            while (s.next()){
                Map<String,Object> books = new HashMap<String,Object>();
                books.put("Books",s.getString("books.title"));
                books.put("Date",s.getDate("borrow.borrow_date"));
                ll.add(books);
            }
        }catch (Exception e){
            System.out.println("Pending Exception");
            System.out.println(e.getMessage());
        }
        return ll;
    }

    ObservableList Books(){
        String one = "select title,author from books";
        ResultSet rs;
        ObservableList<Map<String, Object>> ll = FXCollections.<Map<String, Object>>observableArrayList();
        try{
            rs = stmt.executeQuery(one);
            while (rs.next()){
                Map<String,Object> books = new HashMap<String,Object>();
                books.put("Books",rs.getString("title"));
                books.put("Author",rs.getString("author"));
                ll.add(books);
            }
        }catch (Exception e){
            System.out.println("All Books Exception");
            System.out.println(e.getMessage());
        }
        return ll;
    }

    void addUser(String username, String password, String role){
        int userid = rand.nextInt(10000);
        String one = "insert into users values("+userid+",'"+username+"','"+password+"','"+role+"')";
        try{
            stmt.executeUpdate(one);
            System.out.println("Add user Successful!");
        }catch (Exception e){
            System.out.println("Add User Exception");
            System.out.println(e.getMessage());
        }
    }

    void delUser(String user, String pass){
        String one = "delete from users where username='"+user+"' and pass='"+pass+"';";
        try{
            stmt.executeUpdate(one);
            System.out.println("Del user Successful!");
        }catch (Exception e){
            System.out.println("Del User Exception");
            System.out.println(e.getMessage());
        }
    }

    void addBook(String title, String author){
        int book_id = rand.nextInt(10000);
        String one = String.format("insert into books values(%d,'%s','%s')",book_id,title,author);
        try {
            stmt.executeUpdate(one);
            System.out.println("Add Book Successful!");
        }catch (Exception e){
            System.out.println("Add Book Exception");
            System.out.println(e.getMessage());
        }
    }

    void delBook(String title, String author){
        int book_id = rand.nextInt(10000);
        String one = String.format("delete from books where title='%s' and author='%s'", title, author);
        try {
            stmt.executeUpdate(one);
            System.out.println("Book Delete Successful!");
        }catch (Exception e){
            System.out.println("Book Delete Exception");
            System.out.println(e.getMessage());
        }
    }

    void updateBook(String oldname, String oldauth, String newname, String newauth){
        int book_id=-1;
        String one = String.format("Select book_id from books where title='%s' and author='%s'",oldname,oldauth);
        String two;
        try{
            ResultSet rs = stmt.executeQuery(one);
            while (rs.next()){
                book_id = rs.getInt("book_id");
                System.out.println(book_id);
            }
            if (book_id!=-1){
                two = String.format("update books set title='%s' where book_id=%d",newname,book_id);
                stmt.executeUpdate(two);
                two = String.format("update books set author='%s' where book_id=%d",newauth,book_id);
                stmt.executeUpdate(two);
                System.out.println("Update Book successful!");
            }
        }catch (Exception e){
            System.out.println("Update Book Exception");
            System.out.println(e.getMessage());
        }
    }
}
