public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength cm = new QuantityLength(2.54, LengthUnit.CENTIMETER);

        // =========================
        // EQUALITY
        // =========================
        System.out.println("EQUALITY TEST:");
        System.out.println(feet.equals(inch)); // true

        // =========================
        // CONVERSION
        // =========================
        System.out.println("\nCONVERSION TEST:");
        System.out.println("1 FEET → INCH = " + feet.convertTo(LengthUnit.INCH));
        System.out.println("1 YARD → FEET = " + yard.convertTo(LengthUnit.FEET));
        System.out.println("2.54 CM → INCH = " + cm.convertTo(LengthUnit.INCH));

        // =========================
        // ADDITION (UC6 + UC7)
        // =========================
        System.out.println("\nADDITION TEST:");

        System.out.println(
                QuantityLength.add(feet, inch, LengthUnit.FEET)
        );

        System.out.println(
                QuantityLength.add(feet, inch, LengthUnit.INCH)
        );

        System.out.println(
                QuantityLength.add(feet, inch, LengthUnit.YARD)
        );

        System.out.println(
                QuantityLength.add(yard,
                        new QuantityLength(3.0, LengthUnit.FEET),
                        LengthUnit.YARD)
        );
    }
}