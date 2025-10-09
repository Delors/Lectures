import java.text.DateFormat;
import java.text.ParseException;

void main(String[] args) throws ParseException {
    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        IO.println("Thats the day: " + df.parse(args[0]));
}