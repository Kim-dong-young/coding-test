package codingtest.programmers.lv2;

// [PCCP 기출문제] 2번 / 석유 시추
// https://school.programmers.co.kr/learn/courses/30/lessons/250136
public class Test241231_1 {
	public static void main(String[] args) {
		int[][] land = {
				{0, 0, 0, 1, 1, 1, 0, 0}, 
				{0, 0, 0, 0, 1, 1, 0, 0}, 
				{1, 1, 0, 0, 0, 1, 1, 0}, 
				{1, 1, 1, 0, 0, 0, 0, 0}, 
				{1, 1, 1, 0, 0, 0, 1, 1}
			};
		
		// 시추 포인트
		for(int sichu=0; sichu < land[0].length; sichu++) {
			// 밑으로 시추
			for(int depth=0; depth < land.length; depth++) {
				if(land[depth][sichu] == 0) continue; // 그냥 땅일경우 지나침
				
				// 1. 석유일 경우 석유의 개수 세기
				// 2. 이미 센 석유 덩어리인지 확인하기
			}
		}
	}
}
