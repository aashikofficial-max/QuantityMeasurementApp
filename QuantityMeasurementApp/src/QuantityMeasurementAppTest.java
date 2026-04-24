import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.QuantityLength.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    public void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.QuantityLength.convert(24.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    public void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityMeasurementApp.QuantityLength.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.YARD,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                QuantityMeasurementApp.QuantityLength.convert(2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETER,
                        QuantityMeasurementApp.LengthUnit.INCH),
                1e-3); // slightly relaxed tolerance
    }

    @Test
    public void testConversion_RoundTrip() {
        double original = 5.0;

        double converted = QuantityMeasurementApp.QuantityLength.convert(
                original,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH);

        double back = QuantityMeasurementApp.QuantityLength.convert(
                converted,
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(original, back, EPSILON);
    }

    @Test
    public void testConversion_Zero() {
        assertEquals(0.0,
                QuantityMeasurementApp.QuantityLength.convert(0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    public void testConversion_Negative() {
        assertEquals(-12.0,
                QuantityMeasurementApp.QuantityLength.convert(-1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_NullUnit() {
        QuantityMeasurementApp.QuantityLength.convert(1.0, null,
                QuantityMeasurementApp.LengthUnit.FEET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_InvalidValue() {
        QuantityMeasurementApp.QuantityLength.convert(Double.NaN,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH);
    }
}