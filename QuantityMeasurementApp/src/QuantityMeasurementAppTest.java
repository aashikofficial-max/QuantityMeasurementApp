import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double DELTA = 0.000001;

    // =========================
    // EQUALITY
    // =========================
    @Test
    public void testEquality_Feet_Inch() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(feet.equals(inch));
    }

    // =========================
    // CONVERSION
    // =========================
    @Test
    public void testConvert_Feet_To_Inch() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(12.0, feet.convertTo(LengthUnit.INCH), DELTA);
    }

    @Test
    public void testConvert_Yard_To_Feet() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);

        assertEquals(3.0, yard.convertTo(LengthUnit.FEET), DELTA);
    }

    @Test
    public void testConvert_CM_To_Inch() {
        QuantityLength cm = new QuantityLength(2.54, LengthUnit.CENTIMETER);

        assertEquals(1.0, cm.convertTo(LengthUnit.INCH), DELTA);
    }

    // =========================
    // ADDITION
    // =========================
    @Test
    public void testAddition_Feet_Inch_FeetResult() {

        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(feet, inch, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_Yard_Feet_YardResult() {

        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(yard, feet, LengthUnit.YARD);

        assertEquals(2.0, result.getValue(), DELTA);
    }

    // =========================
    // NULL CHECK
    // =========================
    @Test(expected = IllegalArgumentException.class)
    public void testNullUnit() {
        new QuantityLength(1.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullAddition() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength.add(feet, null, LengthUnit.FEET);
    }
}