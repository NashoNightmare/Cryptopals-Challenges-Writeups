import java.util.Map;
import java.util.HashMap;

public class hex2base{
	public static void main(String[] args){
		String hex_string = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
		String binary_stream = covertHexToBase64(hex_string);
		System.out.println(binary_stream);
	}
	
	public static String covertHexToBase64(String hex){
		
		// Lookup Table Building - Binary to Base64 Mapping
		Map<String, String> map = new HashMap<>();
		String Base64CharSet1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		for(int i=0; i<Base64CharSet1.length(); i++){
			map.put( Integer.toString(i), Base64CharSet1.charAt(i)+"");
		}
		
		// Converting Hex to Binary
		StringBuilder binary = new StringBuilder();
		for (int i=0; i< hex.length(); i++){
			int dec = Integer.parseInt(hex.charAt(i) + "" , 16);
			StringBuilder bin = new StringBuilder(Integer.toBinaryString(dec));
			while(bin.length() < 4){
				bin.insert(0, "0");
			}
			binary.append(bin);
		}

		// Converting Binary to Base64 using the Lookup Table
		StringBuilder b64 = new StringBuilder();
		for (int i=0; i<binary.length(); i=i+6){
			String temp = binary.substring(i, i+6);
			int dec = Integer.parseInt(temp, 2);
			b64.append((String) map.get(Integer.toString(dec)));
		}
		
		return b64.toString();
	}
}




