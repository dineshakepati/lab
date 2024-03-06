package units;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class UnitConverterLogic {
    public static boolean performConversion(WebDriver driver, String fromUnit, String toUnit, double value, double expectedValue) {
        WebDriverWait wait = new WebDriverWait(driver, 30); // 30-second timeout

        try {
            WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("fromInput")));
            fromInput.clear();
            fromInput.sendKeys(String.valueOf(value));
            System.out.println("Entered value: " + value);

            WebElement fromDropdown = driver.findElement(By.id("fromDropdown"));
            fromDropdown.sendKeys(fromUnit);
            System.out.println("Selected from unit: " + fromUnit);

            WebElement toDropdown = driver.findElement(By.id("toDropdown"));
            toDropdown.sendKeys(toUnit);
            System.out.println("Selected to unit: " + toUnit);

            WebElement submitButton = driver.findElement(By.id("submitButton"));
            submitButton.click();
            System.out.println("Clicked Convert button");

            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
            String result = resultElement.getText();
            System.out.println("Conversion result: " + result);

            if (result.contains("=")) {
                String[] resultParts = result.split("=");
                if (resultParts.length > 1) {
                    double convertedValue = Double.parseDouble(resultParts[1].trim().replaceAll("[^\\d.]", ""));

                    double expectedConvertedValue = ConversionHelper.convertValue(value, fromUnit, toUnit);

                    return Math.abs(convertedValue - expectedConvertedValue) < 0.01;
                }
            } else {
                System.err.println("Unexpected format of the conversion result. Test failed.");
            }
        } catch (TimeoutException e) {
            ConversionExceptionHandler.handleException(e);
            return false;
        } catch (Exception e) {
            ConversionExceptionHandler.handleException(e);
            return false;
        }

        return false;
    }

    static class ConversionHelper {
        public static double convertValue(double value, String fromUnit, String toUnit) {
            // Example placeholder conversion logic
            if ("cm".equals(fromUnit) && "meters".equals(toUnit)) {
                return value / 100;
            } else if ("meters".equals(fromUnit) && "cm".equals(toUnit)) {
                return value * 100;
            } else {
                return value;
            }
        }
    }

    static class ConversionExceptionHandler {
        public static void handleException(Exception e) {
            e.printStackTrace();
        }
    }
}
