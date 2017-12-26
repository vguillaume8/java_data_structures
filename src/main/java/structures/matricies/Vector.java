package structures.matricies;

import structures.util.Util;

public final class Vector<T> {

    private Number[] vector;

    public Vector(int dimensionality) {

        // TODO - Force the user to send in an array so we have <T> at runtime

        if (dimensionality < 0) {
            throw new IllegalArgumentException("Vector dimensionality must be at least 1");
        }

        vector = new Number[dimensionality];
    }

    public Vector(Number[] vector) {
        this.vector = vector;
    }

    public Vector<Number> crossProduct(Vector<Number> vector) {

        return null;
    }

    public int dimensionality() {
        return this.vector.length;
    }

    public Number dotProduct(Vector<Number> vector) {
        Number sum;
        int dimensionality;

        dimensionality = vector.dimensionality();

        if (this.dimensionality() != dimensionality) {
            throw new IllegalArgumentException("Vectors must be of same dimensionality");
        }

        return null;
    }

    public Number get(int component) {
        if (component < 0 || component > this.dimensionality()) {
            throw new IllegalArgumentException(" Component must be within the dimensionality of the vector");
        }

        return this.vector[component - 1];
    }

    public Number[] toArray() {
        return this.vector;
    }

    public String toString() {
        String string = Util.ArrayToString(this.toArray());

        string.replace('[', '<');
        string.replace(']', '>');

        return string;
    }
}
