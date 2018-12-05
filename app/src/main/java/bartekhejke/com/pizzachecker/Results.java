package bartekhejke.com.pizzachecker;

/**
 * Created by Bartek on 18.11.2018.
 */

public class Results {

    private int diameter;
    private double price, result;

    public Results(double price, int diameter) {
        this.price = price;
        this.diameter = diameter;
        priceCM();
    }

    private void priceCM(){

        result = (Math.PI*Math.pow(diameter/2,2))/price;
    }

    public double getResult() {
        return result;
    }

    @Override
    public String toString() {
        String resultString = String.format("%.2f",result);
        return resultString;
    }
}
