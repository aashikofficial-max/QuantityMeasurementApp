import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                        new QuantityMeasurementApp.QuantityLength(12.0 / 12, QuantityMeasurementApp.LengthUnit.FEET),
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertEquals(2.0, result.value(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                        new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                        QuantityMeasurementApp.LengthUnit.INCH
                );

        assertEquals(24.0, result.value(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                        new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                        QuantityMeasurementApp.LengthUnit.YARD
                );

        assertEquals(0.666666, result.value(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullTargetUnit() {
        QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                null
        );
    }

    @Test
    public void testAddition_Commutativity() {
        QuantityMeasurementApp.QuantityLength a =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength b =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength r1 =
                QuantityMeasurementApp.QuantityLength.add(a, b, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength r2 =
                QuantityMeasurementApp.QuantityLength.add(b, a, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(r1.value(), r2.value(), EPSILON);
    }
}