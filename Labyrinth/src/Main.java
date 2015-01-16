
public class Main {
	public static void main(String[] args) {
		Labyrinth l = new Labyrinth("test2.txt");
		l.findPath();
		System.out.print(l.toString());
	}
}
