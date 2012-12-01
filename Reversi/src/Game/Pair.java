
public class Pair <X,Y> {
	private final X x;
	private final Y y;
	
	Pair(X x, Y y){
		this.x = x;
		this.y = y;
	}
	
	public X getFirst(){
		return x;
	}
	
	public Y getSecond(){
		return y;
	}
}
