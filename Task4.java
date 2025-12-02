import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter base currency (e.g., USD): ");
            String baseCurrency = scanner.next().toUpperCase();

            System.out.print("Enter target currency (e.g., INR): ");
            String targetCurrency = scanner.next().toUpperCase();

            System.out.print("Enter amount to convert: ");
            double amount = scanner.nextDouble();

            String apiUrl = "https://api.exchangerate.host/latest?base=" + baseCurrency + "&symbols=" + targetCurrency;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String json = response.toString();
            String searchKey = "\"" + targetCurrency + "\":";
            int startIndex = json.indexOf(searchKey) + searchKey.length();
            int endIndex = json.indexOf("}", startIndex);
            String rateStr = json.substring(startIndex, endIndex).trim();
            double exchangeRate = Double.parseDouble(rateStr);

            double convertedAmount = amount * exchangeRate;

            System.out.println("\n--- Conversion Result ---");
            System.out.println(amount + " " + baseCurrency + " = " + convertedAmount + " " + targetCurrency);

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}