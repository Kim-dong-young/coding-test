package codingtest.programmers.lv1;

import java.util.HashMap;

// 달리기 경주
// https://school.programmers.co.kr/learn/courses/30/lessons/178871
public class Test241220_1 {
	public static void main(String[] args) {
		String[] players = {"mumu", "soe", "poe", "kai", "mine"};
		String[] callings = {"kai", "kai", "mine", "mine"};
		
		HashMap<String, Integer> rank = new HashMap<>();
		int index = 0;
		for(String player : players) {
			rank.put(player, index++);
		}
		
		for(String calling : callings) {
			int pIndex = rank.get(calling);
			
			String frontPlayer = players[pIndex - 1];
			players[pIndex - 1] = players[pIndex];
			players[pIndex] = frontPlayer;
			
			rank.put(calling, pIndex - 1);
			rank.put(frontPlayer, pIndex);
		}
		
		System.out.println(rank);
	}
}