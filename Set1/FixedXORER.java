public class FixedXORER{
	public static void main(String[] args){
		String input1 = "1c0111001f010100061a024b53535009181c";
		String input2 = "686974207468652062756c6c277320657965";
		String xored_result = xorer(input1,input2);
		System.out.println(xored_result);
	}
	
	public static String hex2bin(String hex){
		StringBuilder binary = new StringBuilder();
		for (int i = 0; i < hex.length() ; i++){
			
			int dec = Integer.parseInt(hex.charAt(i)+"", 16);
			StringBuilder bin = new StringBuilder(Integer.toBinaryString(dec));			
			while ( bin.length() < 4 ){
				bin.insert(0,"0");
			}
			binary.append(bin);
		}
		return binary.toString();
	}
	
	public static String bin2hex(String bin){
		String hexChars = "0123456789abcdef";
		StringBuilder hex = new StringBuilder();
		for (int i = 0; i < bin.length() ; i=i+4){
			int dec = Integer.parseInt(bin.substring(i,i+4), 2);
			hex.append(hexChars.charAt(dec));
		}
		return hex.toString();
	}
			
	
	public static String xorer(String hex1, String hex2){
		String bin1 = hex2bin(hex1);
		String bin2 = hex2bin(hex2);
		
		StringBuilder xoredbin = new StringBuilder();
		
		for (int i = 0; i < bin1.length() ; i++){
			int bit1 = Integer.parseInt(bin1.charAt(i)+"");
			int bit2 = Integer.parseInt(bin2.charAt(i)+"");
			int xoredbit = bit1 ^ bit2;
			xoredbin.append(Integer.toString(xoredbit));
		}
		return bin2hex(xoredbin.toString());
	}
}
