import app.App;
import app.models.Cliente;
import app.models.Projeto;
import app.repository.ConnectionDB;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        Scanner input = new Scanner(System.in);
        app.run(input);
        input.close();
    }
}
