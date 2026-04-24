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

    // =========================
    // UC5 - CONVERSION
    // =========================
    public double convertTo(LengthUnit targetUnit) {
        double base = unit.toBaseUnit(value);
        return targetUnit.fromBaseUnit(base);
    }

    // =========================
    // UC4 - EQUALITY
    // =========================
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double thisBase = this.unit.toBaseUnit(this.value);
        double otherBase = other.unit.toBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < 0.000001;
    }

    // =========================
    // UC6 + UC7 - ADDITION
    // =========================
    public static QuantityLength add(
            QuantityLength q1,
            QuantityLength q2,
            LengthUnit targetUnit
    ) {

        if (q1 == null || q2 == null || targetUnit == null) {
            throw new IllegalArgumentException("Null not allowed");
        }

        double sumBase =
                q1.unit.toBaseUnit(q1.value) +
                        q2.unit.toBaseUnit(q2.value);

        double result = targetUnit.fromBaseUnit(sumBase);

        return new QuantityLength(result, targetUnit);
    }

    // =========================
    // GETTERS
    // =========================
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "QuantityLength{" + value + " " + unit + "}";
    }
}