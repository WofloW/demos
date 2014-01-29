import java.nio.file.Path;
import java.nio.file.Paths;


public class PrefixMapDemo {

	public static void main(String[] args) {
		PrefixMap.debug = true;

		PrefixMap map = new PrefixMap(2);

		Path input = Paths.get("src", PrefixMapDemo.class.getName() + ".java");
		Path output = Paths.get("prefixmap.txt");

		int lines = PrefixMapParser.loadMap(map, input);

		System.out.println("Processed " + lines + " lines from " +
				input.getFileName() + ".");
		System.out.println("Found " + map.numPrefix() + " prefixes of size " +
				map.prefixSize + ".");

		System.out.println("Saving prefix map as " + output.getFileName() + ".\n");

		PrefixMapParser.toConsole(map);
		PrefixMapParser.toFile(map, output);
	}

}
