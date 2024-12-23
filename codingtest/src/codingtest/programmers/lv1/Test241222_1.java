package codingtest.programmers.lv1;

// [PCCE 기출문제] 9번 / 이웃한 칸
// https://school.programmers.co.kr/learn/courses/30/lessons/250125
public class Test241222_1 {
	public static void main(String[] args) {
		String[][] board = {
				{"blue", "red", "orange", "red"}, 
				{"red", "red", "blue", "orange"}, 
				{"blue", "orange", "red", "red"}, 
				{"orange", "orange", "red", "blue"}
		};
		int h=1;
		int w=1;
		
        int answer = 0;
        
        for(int i=h-1; i <= h+1; i++){
            for(int j=w-1; j <= w+1; j++){
            	
            	// 보드 범위를 벗어날 경우
                if(i < 0 || j < 0) 
                    continue;
                if(i >= board.length || j >= board[i].length)
                    continue;
                
                // 자기 자신일 경우
                if(i==h && j==w)
                    continue;
                
                // 대각선일 경우
                if( (i>h && j>w) || (i<h && j<w) ) // 대각선 ( \ )
                    continue;
                if( (i>h && j<w) || (i<h && j>w) ) // 대각선( / )
                    continue;
                
                if(board[i][j].equals(board[h][w]))
                    answer++;
            }
        }
        
        System.out.println(answer);
	}
}
