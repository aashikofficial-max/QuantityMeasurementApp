import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ================= SAME UNIT =================
    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.FEET
        );

        assertTrue(result.equals(
                new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET)
        ));
    }

    // ================= CROSS UNIT =================
    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = q1.add(q2);

        assertTrue(result.equals(
                new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET)
        ));
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        var result = q1.add(q2);

        assertTrue(result.equals(
                new QuantityMeasurementApp.QuantityLength(24.0, QuantityMeasurementApp.LengthUnit.INCH)
        ));
    }

    @Test
    public void testAddition_YardPlusFeet() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARD),
                new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.YARD
        );

        assertTrue(result.equals(
                new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARD)
        ));
    }

    // ================= CENTIMETER =================
    @Test
    public void testAddition_CentimeterPlusInch() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETER),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.CENTIMETER
        );

        assertEquals(5.08,
                result.convertTo(QuantityMeasurementApp.LengthUnit.CENTIMETER).getValue(),
                1e-2);
    }

    // ================= COMMUTATIVE =================
    @Test
    public void testAddition_Commutativity() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(a.add(b).equals(b.add(a)));
    }

    // ================= ZERO =================
    @Test
    public void testAddition_WithZero() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.FEET
        );

        assertTrue(result.equals(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET)
        ));
    }

    // ================= NEGATIVE =================
    @Test
    public void testAddition_NegativeValues() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.FEET
        );

        assertTrue(result.equals(
                new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET)
        ));
    }

    // ================= NULL CHECK =================
    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullOperand() {
        QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                null,
                QuantityMeasurementApp.LengthUnit.FEET
        );
    }
}