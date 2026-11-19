import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private final String region;
    private final double monthlyBudget;

    private Configuration() {
        Properties properties = new Properties();

        try (FileInputStream input =
                     new FileInputStream("config.properties")) {

            properties.load(input);

            region = properties.getProperty("region");
            monthlyBudget = Double.parseDouble(
                    properties.getProperty("monthly.budget")
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao carregar config.properties", e
            );
        }
    }

    private static class Holder {
        private static final Configuration INSTANCE =
                new Configuration();
    }

    public static Configuration getInstance() {
        return Holder.INSTANCE;
    }

    public String getRegion() {
        return region;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }
}