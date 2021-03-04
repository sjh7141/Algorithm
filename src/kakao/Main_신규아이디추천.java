package kakao;

public class Main_신규아이디추천 {

	public static void main(String[] args) {
		String new_id = "";
	 	String answer = "";
		//1단계 
		StringBuilder sb = new StringBuilder(new_id.toLowerCase());
		//2단계
		levelTwo(sb);
		//3단계;
		levelThree(sb);
		//4단계
		if(sb.length() > 0 && sb.charAt(0) == '.') sb.deleteCharAt(0);
		if(sb.length() > 1 && sb.charAt(sb.length()-1) == '.') sb.deleteCharAt(sb.length()-1);
		//5단계
		if(sb.length() == 0) sb = new StringBuilder("a");
		//6단계
		if(sb.length() >= 16) {
			sb.delete(15, sb.length());
			if(sb.charAt(14) == '.') sb.deleteCharAt(14);
		}
		//7단계
		while(sb.length() < 3) {
			sb.append(sb.charAt(sb.length()-1));
		}
		answer = sb.toString();
		System.out.println(answer);
    }
	private static void levelThree(StringBuilder sb) {
		boolean flag = false;
		for(int i=0; i<sb.length(); i++) {
			if(sb.charAt(i) == '.') {
				if(flag) {
					sb.deleteCharAt(i--);
				}else {
					flag = true;
				}
			}else {
				flag = false;
			}
		}
		
	}

	private static void levelTwo(StringBuilder sb) {
		for(int i=0; i<sb.length(); i++) {
			if(sb.charAt(i) == '-' || sb.charAt(i) == '_' || sb.charAt(i) == '.') continue;
			if(sb.charAt(i) >= '0' && sb.charAt(i) <= '9') continue;
			if(sb.charAt(i) >= 'a' && sb.charAt(i) <= 'z') continue;
			sb.deleteCharAt(i--);
		}	
		
	}

}
