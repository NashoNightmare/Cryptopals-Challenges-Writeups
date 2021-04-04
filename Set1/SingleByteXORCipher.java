import java.util.Map;
import java.util.HashMap;

public class SingleByteXORCipher{
	public static void main(String args[]){
		String hex_stream = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
		singleXorDecipher(hex_stream);
		
	}
	
	public static String hex2bin(String hex){
		StringBuilder binary = new StringBuilder();
		for (int i=0; i<hex.length(); i++){
			int dec = Integer.parseInt(hex.charAt(i)+"", 16);
			StringBuilder bit = new StringBuilder(Integer.toBinaryString(dec));
			while (bit.length() < 4){
				bit.insert(0, '0');
			}
			binary.append(bit);
		}
		return binary.toString();
	}
	
	public static String text2hex(String text){
		String hexMap = "0123456789abcdef";
		
		byte[] bytes = text.getBytes();
		StringBuilder hex = new StringBuilder();
		for (byte b: bytes){
			int val = b;
			StringBuilder bit_stream = new StringBuilder(Integer.toBinaryString(val));
			while (bit_stream.length() < 8){
				bit_stream.insert(0, '0');
			}
			for (int i=0; i<bit_stream.length(); i=i+4){
				int dec = Integer.parseInt(bit_stream.substring(i,i+4), 2);
				String hex_val = hexMap.charAt(dec)+"";
				hex.append(hex_val);
			}
		}
		return hex.toString();
	}
	
	public static String binary2text(String binary){
		byte[] bytes = new byte[binary.length()/8];
		int p = 0;
		for (int i=0; i<binary.length(); i=i+8){
			int dec = Integer.parseInt(binary.substring(i, i+8), 2);
			bytes[p] = (byte) dec;
			p++;
		}
		String text = new String(bytes);
		return text;
	}
	
	public static double scoreText(String text){
		// Lookup Tables
		Map<String,String> textFreq = new HashMap<>(); // HASHMAP FOR FREQUENCY ANALYSIS
		String[] freq = {"0.08167", "0.01492", "0.02782", "0.04253", "0.12702", "0.02228", "0.02015", "0.06094", "0.06094", "0.00153", "0.00772", "0.04025", "0.02406", "0.06749", "0.07507", "0.01929", "0.00095", "0.05987", "0.06327", "0.09056", "0.02758", "0.00978", "0.02360", "0.00150", "0.01974", "0.00074", "0.13000"};
		String alphabet = "abcdefghijklmnopqrstuvwxyz ";
		
		for (int i=0; i<alphabet.length(); i++){
			textFreq.put(alphabet.charAt(i)+"", freq[i]);
		}	
		
		double score = 0;
		for (int i=0; i < text.length(); i++){
			try{
				score += Double.parseDouble(textFreq.get(text.charAt(i)+""));
			} catch (NullPointerException e1){
			}
		}
		
		return score;
	}
			
	
	public static void singleXorDecipher(String cipher){
		// Lookup Tables
		String keys = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; //PAYLOAD
		
		String deciphered_msg = new String();
		String binary_cipher = hex2bin(cipher);
		double maxScore = 0;
		String maxScored_text = new String();
		
		for (int i=0; i<keys.length(); i++){
			String binary_key = hex2bin(text2hex(keys.charAt(i)+""));
			int k = 0;
			StringBuilder xored_binary = new StringBuilder();
			for (int j=0; j<binary_cipher.length(); j++){
				int xored_bit = Integer.parseInt(binary_cipher.charAt(j)+"", 2) ^ Integer.parseInt(binary_key.charAt(k)+"", 2);
				k = k + 1;
				if (k==binary_key.length()){
					k = 0;
				}
				xored_binary.append(xored_bit);
			}
			String xored_text = binary2text(xored_binary.toString());
			double score = scoreText(xored_text);
			if (score > maxScore){
				maxScore = score;
				maxScored_text = xored_text;
			}
		}
		System.out.println(maxScored_text);
	}
}

		
