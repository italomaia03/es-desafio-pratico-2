import app.App;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        Scanner input = new Scanner(System.in);
        app.run(input);
        input.close();
    }
}
