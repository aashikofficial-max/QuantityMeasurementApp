public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // ================= LENGTH =================
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Length Equality:");
        System.out.println(l1.equals(l2)); // true

        System.out.println("Length Convert:");
        System.out.println(l1.convertTo(LengthUnit.INCH)); // 12

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


        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

        System.out.println("\nWeight Equality:");
        System.out.println(w1.equals(w2)); // true

        System.out.println("Weight Convert:");
        System.out.println(w1.convertTo(WeightUnit.GRAM)); // 1000

        System.out.println("Weight Add:");
        QuantityWeight w3 = QuantityWeight.add(w1, w2);
        System.out.println(w3); // 2 kg

        System.out.println("Weight Add with target:");
        QuantityWeight w4 = QuantityWeight.add(w1, w2, WeightUnit.POUND);
        System.out.println(w4);
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