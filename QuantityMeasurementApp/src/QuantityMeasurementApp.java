public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // ================= LENGTH =================
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Length Equality:");
        System.out.println(l1.equals(l2)); // true

        System.out.println("Length Convert:");
        System.out.println(l1.convertTo(LengthUnit.INCH)); // 12

        System.out.println("Length Add:");
        QuantityLength l3 = QuantityLength.add(l1, l2, LengthUnit.FEET);
        System.out.println(l3); // 2 feet


        // ================= WEIGHT =================
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

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
}