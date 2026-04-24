public class QuantityMeasurementApp {

    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // ================= VALUE OBJECT =================
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        private static final double EPSILON = 1e-6;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        public static double convert(double value, LengthUnit source, LengthUnit target) {
            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            double feet = source.toFeet(value);
            return target.fromFeet(feet);
        }

        public QuantityLength convertTo(LengthUnit target) {
            return new QuantityLength(convert(value, unit, target), target);
        }

        public QuantityLength add(QuantityLength other) {
            return add(this, other, this.unit);
        }

        public static QuantityLength add(QuantityLength q1,
                                         QuantityLength q2,
                                         LengthUnit targetUnit) {

            if (q1 == null || q2 == null)
                throw new IllegalArgumentException("Operands cannot be null");
            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double sumFeet = q1.toFeet() + q2.toFeet();
            double result = targetUnit.fromFeet(sumFeet);

            return new QuantityLength(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(this.toFeet() - other.toFeet()) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        QuantityLength length1 = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(12, LengthUnit.INCH);

        // Equality check
        System.out.println("1 foot == 12 inches ? " + length1.equals(length2));

        // Conversion
        QuantityLength inYards = length1.convertTo(LengthUnit.YARD);
        System.out.println("1 foot in yards: " + inYards);

        // Addition (default unit = first operand unit)
        QuantityLength sum1 = length1.add(length2);
        System.out.println("1 foot + 12 inches = " + sum1);

        // Addition with target unit
        QuantityLength sum2 = QuantityLength.add(length1, length2, LengthUnit.INCH);
        System.out.println("1 foot + 12 inches in inches = " + sum2);

        // Another example
        QuantityLength cm = new QuantityLength(30, LengthUnit.CENTIMETER);
        System.out.println("30 cm in feet: " + cm.convertTo(LengthUnit.FEET));
    }
}