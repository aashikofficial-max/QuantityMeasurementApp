public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // convert this unit → feet (base unit)
    public double toBaseUnit(double value) {
        return value * toFeetFactor;
    }

    // convert feet → this unit
    public double fromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }
}