public class Letter {
	Letter() {
		System.out.print("a ");
	}

	{
		System.out.print("b ");
	}

	public static void main(String[] args) {
		new Letter().go();
	}

	void go() {
		System.out.print("c ");
	}

	static {
		System.out.print("d ");
	}

}
