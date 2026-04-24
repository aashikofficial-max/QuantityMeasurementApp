import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    // ===================== LENGTH TESTS =====================

    @Test
    public void testLength_FeetToFeet_SameValue() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testLength_FeetToInch_EqualValue() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCH);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testLength_FeetToInch_Conversion() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(12.0, l1.convertTo(LengthUnit.INCH), 0.0001);
    }

    @Test
    public void testLength_Addition_FeetPlusInch() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = QuantityLength.add(l1, l2, LengthUnit.FEET);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testLength_Addition_InchPlusFeet_TargetInch() {
        QuantityLength l1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength l2 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(l1, l2, LengthUnit.INCH);

        assertEquals(new QuantityLength(24.0, LengthUnit.INCH), result);
    }

    @Test
    public void testLength_NullComparison() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertFalse(l1.equals(null));
    }

    @Test
    public void testLength_DifferentTypeComparison() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        Object obj = new Object();
        assertFalse(l1.equals(obj));
    }

    // ===================== WEIGHT TESTS =====================

    @Test
    public void testWeight_KgToKg_SameValue() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    public void testWeight_KgToGram_EqualValue() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    public void testWeight_KgToPound_Conversion() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(2.20462, w1.convertTo(WeightUnit.POUND), 0.0001);
    }

    @Test
    public void testWeight_Addition_KgPlusGram() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = QuantityWeight.add(w1, w2);

        assertEquals(new QuantityWeight(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    public void testWeight_Addition_WithTargetUnit_Pound() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = QuantityWeight.add(w1, w2, WeightUnit.POUND);

        assertEquals(result.convertTo(WeightUnit.POUND),
                new QuantityWeight(2.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND),
                0.0001);
    }

    @Test
    public void testWeight_NegativeValue() {
        QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(-2.0, WeightUnit.KILOGRAM);

        QuantityWeight result = QuantityWeight.add(w1, w2);

        assertEquals(new QuantityWeight(3.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    public void testWeight_ZeroAddition() {
        QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(0.0, WeightUnit.GRAM);

        QuantityWeight result = QuantityWeight.add(w1, w2);

        assertEquals(w1, result);
    }

    // ===================== CROSS CATEGORY SAFETY =====================

    @Test
    public void testLengthVsWeight_NotEqual() {
        QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // ===================== NULL SAFETY =====================

    @Test(expected = IllegalArgumentException.class)
    public void testWeight_NullUnit_ShouldThrow() {
        new QuantityWeight(1.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeight_NaN_ShouldThrow() {
        new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeight_Infinite_ShouldThrow() {
        new QuantityWeight(Double.POSITIVE_INFINITY, WeightUnit.KILOGRAM);
    }
}