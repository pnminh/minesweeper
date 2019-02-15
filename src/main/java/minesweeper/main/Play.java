package minesweeper.main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Play {
	public static void main(String[] args) {
		// get inputs
		Scanner reader = new Scanner(System.in);
		boolean isContinue = true;
		// play again until E is entered
		while (isContinue) {
			System.out.print("Enter numbers:");
			// require format row column noOfMines
			String line = reader.nextLine();
			String[] input = line.split(" ");
			int boardRow = Integer.parseInt(input[0]);
			int boardCol = Integer.parseInt(input[1]);
			int noOfMines = Integer.parseInt(input[2]);
			// create the 2-dimension board array with 0 as initial values
			int[][] board = new int[boardRow][boardCol];
			/*
			 * create random positions for mines. The position should be between 0 and the
			 * max position(maxPos). E.g for 10*10 array, position 0 should be for row 0 col
			 * 0, position 10 should be for row 1 col 0
			 */
			// Step 1: create the array from 0 to maxPos (boardRow*boardCol-1)
			Integer[] positions = new Integer[boardRow * boardCol];
			for (int i = 0; i < positions.length; i++) {
				positions[i] = i;
			}
			// Step 2: shuffle the array to create random positions
			List<Integer> randomPositionList = Arrays.asList(positions);
			Collections.shuffle(randomPositionList);

			// Step 3: only use the first noOfMines items from the position arrays
			for (int i = 0; i < noOfMines; i++) {
				// convert a flat position to a 2-dimension one
				// e.g. for the 11*12 board, a flat position 100 will be 100/12,100%12= 8,4
				int row = randomPositionList.get(i) / boardCol;
				int col = randomPositionList.get(i) % boardCol;
				// mine position has the value 9
				board[row][col] = 9;
				// add 1 to all positions neighboring to the mine position
				for (int j = row - 1; j <= row + 1; j++) {
					for (int k = col - 1; k <= col + 1; k++) {
						// mines at the boundary only have partial neighbors
						if (j >= 0 && j <= board.length - 1 && k >= 0 && k <= board[0].length - 1 && board[j][k] != 9) {
							board[j][k] += 1;
						}
					}
				}
			}
			for (int[] row : board) {
				System.out.println(Arrays.toString(row));
			}
			System.out.println("Press any key to play again or E to end:");
			line = reader.nextLine();
			if (line.equals("E")) {
				reader.close();
				System.exit(0);
			}
			continue;
		}

	}
}
