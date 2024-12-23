package codingtest.programmers.lv1;

// 크기가 작은 부분 문자열
// https://school.programmers.co.kr/learn/courses/30/lessons/147355
public class Test241223_1 {
	public static void main(String[] args) {
		String t = "500220839878";
		String p = "7";
		
		int result=0;
		
		for(int i=0; i< t.length() - p.length() + 1; i++) {
			String subStr = t.substring(i, i + p.length());
			System.out.println("subStr : " + subStr);
			if(subStr.compareTo(p) <= 0) {
				System.out.println(" 더 작음 ");
				result++;
			}
		}
		
		System.out.println(result);
	}
}
