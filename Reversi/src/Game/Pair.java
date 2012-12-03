package Game;

/**
 * 
 * The following class represents a pair of two generic types.
 * 
 * @param <X>
 *            Type of first element
 * @param <Y>
 *            Type of second element
 */
public class Pair<X, Y> {
	private final X x;
	private final Y y;

	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	public X getFirst() {
		return x;
	}

	public Y getSecond() {
		return y;
	}
}
