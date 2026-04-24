import java.util.Objects;

public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    public double convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Null unit");

        double kg = unit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(kg);
    }

    public static QuantityWeight add(QuantityWeight w1, QuantityWeight w2, WeightUnit targetUnit) {
        if (w1 == null || w2 == null || targetUnit == null)
            throw new IllegalArgumentException("Null not allowed");

        double sumKg =
                w1.unit.convertToBaseUnit(w1.value) +
                        w2.unit.convertToBaseUnit(w2.value);

        double result = targetUnit.convertFromBaseUnit(sumKg);
        return new QuantityWeight(result, targetUnit);
    }

    public static QuantityWeight add(QuantityWeight w1, QuantityWeight w2) {
        return add(w1, w2, w1.unit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityWeight)) return false;

        QuantityWeight other = (QuantityWeight) obj;

        double thisKg = this.unit.convertToBaseUnit(this.value);
        double otherKg = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisKg - otherKg) < 0.000001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "QuantityWeight{" + value + " " + unit + "}";
    }
}