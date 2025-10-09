import java.text.DateFormat;
import static java.lang.System.err;

void main(String[] args) {
    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
    try {
        IO.println("Thats the day: " + df.parse(args[0]));
    } catch (Exception e) {
        err.println("Error: " + e.getMessage()+
                " Expected format: "+df.format(new Date()));  
    }
}