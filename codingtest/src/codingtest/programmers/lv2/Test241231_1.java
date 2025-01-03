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
	
	public static int countOil(int[][] land, int x, int y) {
		int result = 0;
		CoordStack cStack = new CoordStack();
		
		while(true) {
			// 남쪽 탐색
			if(land[x+1][y] != 0)
				cStack.push(x,y);
			// 동쪽 탐색
			if(land[x][y+1] != 0)
				cStack.push(x,y);
			// 북쪽 탐색
			if(land[x-1][y] != 0)
				cStack.push(x,y);
			// 서쪽 탐색
			if(land[x][y-1] != 0)
				cStack.push(x,y);
			
			// TODO 스택에 탐색할 좌표 저장 후, 더이상 탐색할 곳이 없을떄까지 반복할 것
			cStack.pop();
			result++;
			
			if(cStack.isEmpty())
				break;
		}
		
		
		return result;
	}
}

class CoordStack{
	private int top;
	private int[][] coordArr;
	
	public CoordStack() {
		this.top = -1;
		this.coordArr = new int[10][2];
	}
	
	private boolean isFull() {
		return top == coordArr.length - 1;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	private void increaseStackSize() {
		int[][] newStack = new int[coordArr.length * 2][2];
		System.arraycopy(coordArr, 0, newStack, 0, coordArr.length);
		coordArr = newStack;
	}
	
	public void push(int x, int y) {
		int[] coord = { x, y };
		if(isFull())
			increaseStackSize();
		this.coordArr[++top] = coord;
	}
	
	public int[] pop() {
		if(isEmpty())
			return null;
		else
			return this.coordArr[top--];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i <= top; i++) {
			sb.append("[ x : ")
			.append(coordArr[i][0])
			.append("y : ")
			.append(coordArr[i][1])
			.append(" ] ");
		}
		
		return "CoordStack [top=" + top + ", coordArr=" + sb + "]";
	}
	
}
