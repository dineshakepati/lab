package units;

public class ConversionHelper {
    public static double convertValue(double value, String fromUnit, String toUnit) {
        if ("cm".equals(fromUnit)) {
            if ("meters".equals(toUnit)) {
                return value / 100.0;
            } else if ("inches".equals(toUnit)) {
                return value / 2.54;
            } else if ("km".equals(toUnit)) {
                return value / 100000.0;
            } else if ("feet".equals(toUnit)) {
                return value / 30.48; 
            }
        } else if ("meters".equals(fromUnit)) {
            if ("cm".equals(toUnit)) {
                return value * 100.0;
            } else if ("inches".equals(toUnit)) {
                return value * 39.37;
            } else if ("km".equals(toUnit)) {
                return value / 1000.0;
            } else if ("feet".equals(toUnit)) {
                return value * 3.28084; 
            }
        } else if ("inches".equals(fromUnit)) {
            if ("cm".equals(toUnit)) {
                return value * 2.54;
            } else if ("meters".equals(toUnit)) {
                return value / 39.37;
            } else if ("km".equals(toUnit)) {
                return value / 39370.1;
            } else if ("feet".equals(toUnit)) {
                return value / 12.0; // For example
            }
        } else if ("km".equals(fromUnit)) {
            if ("cm".equals(toUnit)) {
                return value * 100000.0;
            } else if ("meters".equals(toUnit)) {
                return value * 1000.0;
            } else if ("inches".equals(toUnit)) {
                return value * 39370.1;
            } else if ("feet".equals(toUnit)) {
                
                return value * 3280.84; 
            }
        }
        return value;
    }
}
