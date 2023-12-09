public class Triple<T extends Comparable<T>> {
    private T first;
    private T second;
    private T third;

    public Triple(T first, T second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }

    public T min() {
        return minOrMax("min");
    }

    public T max() {
        return minOrMax("max");
    }

    public double mean() {
        if (!(first instanceof Number) || !(second instanceof Number) || !(third instanceof Number)) {
            throw new IllegalArgumentException("Mean is supported only for numeric types");
        }

        double sum = ((Number) first).doubleValue() + ((Number) second).doubleValue() + ((Number) third).doubleValue();
        return sum / 3.0;
    }

    private T minOrMax(String operation) {
        if (first == null || second == null || third == null) {
            throw new IllegalArgumentException("All elements must be non-null");
        }

        T result;

        switch (operation) {
            case "min":
                result = (first.compareTo(second) <= 0) ? (first.compareTo(third) <= 0 ? first : third) : (second.compareTo(third) <= 0 ? second : third);
                break;
            case "max":
                result = (first.compareTo(second) >= 0) ? (first.compareTo(third) >= 0 ? first : third) : (second.compareTo(third) >= 0 ? second : third);
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }

        return result;
    }
}
