package BusinessLayer.Manager;

import BusinessLayer.Entities.Data.Data;

import javax.ejb.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

@Singleton
public class Controller {
    private final SimpleDateFormat sOut = new SimpleDateFormat("hh:mm:ss");
    private final SimpleDateFormat sIn = new SimpleDateFormat("h:m:s");

    public boolean checkXYR(double x, double y, double r) {
        //triangle
        if (x <= 0 && x >= -r && y <= 0 && y >= -r * 0.5 && y >= (-0.5 * x - 0.5 * r)) {
            return true;
        }
        //rectangle
        else if (x <= 0 && x >= -0.5 * r && y >= 0 && y <= r) {
            return true;
        }
        //ring
        else if (x >= 0 && y <= 0 && (x * x + y * y) <= r * r / 4) {
            return true;
        } else return false;
        //hate simplifies
    }

    public Data parseData(Double x, Double y, Double z) {
        double xData = x;
        double yData = y;
        double zData = z;
        Data data = new Data(xData, yData, zData);
        data.setResult(checkXYR(xData, yData, zData));
        return data;
    }

    public String timeFormat(LocalTime time) {
        Date date = null;
        try {
            date = sIn.parse(String.valueOf(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sOut.format(date);
    }
}