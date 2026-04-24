import java.util.Objects;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    public double convertTo(LengthUnit target) {
        double base = unit.toFeet(value);
        return target.fromFeet(base);
    }

    public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit target) {
        double sum =
                q1.unit.toFeet(q1.value) +
                        q2.unit.toFeet(q2.value);

        return new QuantityLength(target.fromFeet(sum), target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) o;

        return Math.abs(
                this.unit.toFeet(this.value) -
                        other.unit.toFeet(other.value)
        ) < 0.000001;
    }

    @Override
    public String toString() {
        return "QuantityLength{" + value + " " + unit + "}";
    }
}