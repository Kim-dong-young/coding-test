package codingtest.programmers.lv2;

// [PCCP 기출문제] 2번 / 석유 시추
// https://school.programmers.co.kr/learn/courses/30/lessons/250136
public class Test241231_1 {
	public static void main(String[] args) {
		/*
			 {
				 {1, 0, 1, 0, 1, 1}, 
				 {1, 0, 1, 0, 0, 0}, 
				 {1, 0, 1, 0, 0, 1}, 
				 {1, 0, 0, 1, 0, 0}, 
				 {1, 0, 0, 1, 0, 1}, 
				 {1, 0, 0, 0, 0, 0}, 
				 {1, 1, 1, 1, 1, 1}
			 }
			 
			 {
				{0, 0, 0, 1, 1, 1, 0, 0}, 
				{0, 0, 0, 0, 1, 1, 0, 0}, 
				{1, 1, 0, 0, 0, 1, 1, 0}, 
				{1, 1, 1, 0, 0, 0, 0, 0}, 
				{1, 1, 1, 0, 0, 0, 1, 1}
			}
		 */
		int[][] land = {
				 {1, 0, 1, 0, 1, 1}, 
				 {1, 0, 1, 0, 0, 0}, 
				 {1, 0, 1, 0, 0, 1}, 
				 {1, 0, 0, 1, 0, 0}, 
				 {1, 0, 0, 1, 0, 1}, 
				 {1, 0, 0, 0, 0, 0}, 
				 {1, 1, 1, 1, 1, 1}
		 };
		
		int[] expectOil = new int[land[0].length];
		
		// 시추 포인트
		for(int sichu=0; sichu < land[0].length; sichu++) {
			boolean[][] isVisited = new boolean[land.length][land[0].length];
			// 밑으로 시추
			for(int depth=0; depth < land.length; depth++) {
				// 그냥 땅이거나, 이미 측정한 땅이면 지나침
				if(land[depth][sichu] == 0 || isVisited[depth][sichu]) continue;
//				System.out.println("depth : " + depth + " sichu : " + sichu);
				// 1. 석유일 경우 석유의 개수 세기
				expectOil[sichu] += countOil(land, isVisited, depth, sichu);
			}
			
//			for(int i=0; i < land.length; i++) {
//				for(int j=0; j < land[0].length; j++) {
//					System.out.print(String.format("%6b", isVisited[i][j]));
//				}
//				System.out.println("");
//			}
//			System.out.println();
		}
		
//		for(int oilCnt : expectOil) {
//			System.out.print(oilCnt + " ");
//		}
		int max = expectOil[0];
		for(int oilCnt : expectOil) {
			max = Math.max(max, oilCnt);
		}
		
		System.out.println(max);
	}
	
	public static int countOil(int[][] land, boolean[][]isVisited, int x, int y) {
		int oilCnt = 0;
		
		CoordStack cStack = new CoordStack();
		cStack.push(x, y);
		
		while(true) {
			// 남쪽 탐색
			if(x+1 < land.length && land[x+1][y] != 0 && !isVisited[x+1][y]) {
				cStack.push(x+1,y);
				isVisited[x+1][y]= true;
			}
			// 동쪽 탐색
			if(y+1 < land[x].length && land[x][y+1] != 0 && !isVisited[x][y+1]) {
				cStack.push(x,y+1);
				isVisited[x][y+1] = true;
			}
			// 북쪽 탐색
			if(x-1 >= 0 && land[x-1][y] != 0 && !isVisited[x-1][y]) {
				cStack.push(x-1,y);
				isVisited[x-1][y] = true;
			}
			// 서쪽 탐색
			if(y-1 >= 0 && land[x][y-1] != 0 && !isVisited[x][y-1]) {
				cStack.push(x,y-1);
				isVisited[x][y-1] = true;
			}
			
//			System.out.println(cStack);
			int[] nextCoord = cStack.pop();
			if(nextCoord != null) {
				isVisited[x][y] = true;
				x = nextCoord[0];
				y = nextCoord[1];
				oilCnt++;
			}
			
			if(cStack.isEmpty())
				break;
		}
		
		
		return oilCnt;
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
		int[] coord = new int[]{ x, y };
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
			.append(" y : ")
			.append(coordArr[i][1])
			.append(" ] ");
		}
		
		return "CoordStack [top=" + top + ", coordArr=" + sb + "]";
	}
	
}
