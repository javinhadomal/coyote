package javinhaDoMal;
import org.rapidoid.annotation.Valid;
import org.rapidoid.annotation.Valid;
import org.rapidoid.jpa.JPA;
import org.rapidoid.setup.On;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	On.page("/hi").mvc("Hello <b>world</b>!");
    }
}


/*

 
public class Main {
 
    public static void main(String[] args) {
        App.bootstrap(args).jpa(); // bootstrap JPA
 
        On.get("/books").json(() -> JPA.of(Book.class).all()); // get all books
 
        On.post("/books").json((@Valid Book b) -> JPA.save(b)); // insert new book if valid
    }
 
}*/