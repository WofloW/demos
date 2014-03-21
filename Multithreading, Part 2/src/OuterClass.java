public class OuterClass {

	private final static String a = "Outer";
	private final String b;

	private final InnerClass inner1;
	private final InnerClass inner2;

	public OuterClass(String x, String y, String z) {
		this.b = x;
		this.inner1 = new InnerClass(y);
		this.inner2 = new InnerClass(z);
	}

	public class InnerClass {
		private final String c;

		public InnerClass(String text) {
			this.c = text;
		}

		@Override
		public String toString() {
			return a + b + c;
		}
	}

	public void print() {
		System.out.println(inner1.toString());
		System.out.println(inner2.toString());
	}

	public static void main(String[] args) {
		OuterClass outer1 = new OuterClass("A", "1", "2");
		OuterClass outer2 = new OuterClass("B", "3", "4");

		outer1.print();
		outer2.print();
	}
}
