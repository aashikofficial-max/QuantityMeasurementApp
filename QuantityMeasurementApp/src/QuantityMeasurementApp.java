public class QuantityMeasurementApp {
    public static void main(String[] args) {

        // =========================
        // CREATE OBJECTS
        // =========================
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);

        // =========================
        // UC4: EQUALITY CHECK
        // =========================
        System.out.println("=== EQUALITY TEST ===");
        System.out.println("1 FEET == 12 INCH ? " + feet.equals(inch));

        // =========================
        // UC5: CONVERSION TEST
        // =========================
        System.out.println("\n=== CONVERSION TEST ===");
        System.out.println("1 FEET to INCH = " + feet.convertTo(LengthUnit.INCH));
        System.out.println("1 YARD to FEET = " + yard.convertTo(LengthUnit.FEET));

        // =========================
        // UC6: ADDITION (DEFAULT UNIT = FIRST OPERAND)
        // =========================
        System.out.println("\n=== UC6 ADDITION ===");

        QuantityLength result1 = QuantityLength.add(feet, inch, LengthUnit.FEET);
        System.out.println("1 FEET + 12 INCH (FEET) = " + result1);

        // =========================
        // UC7: ADDITION (TARGET UNIT SPECIFIED)
        // =========================
        System.out.println("\n=== UC7 ADDITION ===");

        QuantityLength result2 = QuantityLength.add(feet, inch, LengthUnit.INCH);
        System.out.println("1 FEET + 12 INCH (INCH) = " + result2);

        QuantityLength result3 = QuantityLength.add(feet, inch, LengthUnit.YARD);
        System.out.println("1 FEET + 12 INCH (YARD) = " + result3);

        // =========================
        // EXTRA TEST CASES
        // =========================
        System.out.println("\n=== EXTRA TESTS ===");

        QuantityLength result4 =
                QuantityLength.add(
                        new QuantityLength(36.0, LengthUnit.INCH),
                        new QuantityLength(1.0, LengthUnit.YARD),
                        LengthUnit.FEET
                );

        System.out.println("36 INCH + 1 YARD (FEET) = " + result4);

}
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12),
        YARD(3.0),
        CENTIMETER(1.0 / 30.48);

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

    record QuantityLength(double value, LengthUnit unit) {
        QuantityLength {
            if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Invalid input");
            }
        }

        // ✅ UC5 conversion
        public double convertTo(LengthUnit targetUnit) {
            double base = unit.toFeet(value);
            return targetUnit.fromFeet(base);
        }

        // ✅ UC4 equality
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof QuantityLength)) return false;

            QuantityLength that = (QuantityLength) o;

            double thisBase = this.unit.toFeet(this.value);
            double thatBase = that.unit.toFeet(that.value);

            return Math.abs(thisBase - thatBase) < 0.000001;
        }

        // ✅ UC6 + UC7 ADDITION (IMPORTANT FIX)
        public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
            if (q1 == null || q2 == null || targetUnit == null) {
                throw new IllegalArgumentException("Null not allowed");
            }

            double sumInFeet =
                    q1.unit.toFeet(q1.value) +
                            q2.unit.toFeet(q2.value);

            double resultValue = targetUnit.fromFeet(sumInFeet);

            return new QuantityLength(resultValue, targetUnit);
        }

        @Override
        public String toString() {
            return "QuantityLength{" + value + " " + unit + "}";
        }
    }
}