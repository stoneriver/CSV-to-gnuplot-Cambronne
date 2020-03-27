package com.github.stoneriver.csvtognuplot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSVToGnuplot {

	/*
	 * ã‚°ãƒ©ãƒ•ä¸Šã�®å�„ç‚¹ã�¯ã€�è‡ªç„¶æ•°n, mã�«å¯¾ã�—ã�¦(x,y,z)=(X[n], Y[m], Z[m][n])ã�§ä¸Žã�ˆã‚‰ã‚Œã‚‹
	 */
	private static double[] X;
	private static int sizeX;
	private static double[] Y;
	private static int sizeY;
	private static double[][] Z;

	public static void main(String[] args) {
		System.out.println("Hello!");
		try {
			input();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			output();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done!");

	}

	private static void output() throws FileNotFoundException {
		File file = new File("out.dat");
		PrintWriter pw = new PrintWriter(file);
		for (int j = 0; j < sizeX; j++) {
			for (int i = 0; i < sizeY; i++) {
				pw.println(X[j] + "\t" + Y[i] + "\t" + Z[i][j]);
			}
			pw.println();
		}
		pw.close();
	}

	private static void input() throws FileNotFoundException, IOException {
		// å…¥åŠ›
		Scanner in = new Scanner(System.in);
		String fileName = in.next();
		sizeX = in.nextInt();
		sizeY = in.nextInt();
		in.close();

		// å¿…è¦�å¤‰æ•°å®£è¨€
		File file = new File(fileName);
		BufferedReader rd = new BufferedReader(new FileReader(file));
		String line = "";
		String[] tokens;

		// ãƒ‡ãƒ¼ã‚¿æ ¼ç´�é…�åˆ—æº–å‚™
		X = new double[sizeX];
		Y = new double[sizeY];
		Z = new double[sizeY][sizeX];

		/* 1è¡Œç›®ã�®èª­ã�¿è¾¼ã�¿ */
		line = rd.readLine();
		tokens = line.split(",");

		for (int i = 0; i < sizeX; i++) {
			X[i] = Double.valueOf(tokens[i + 1]);
		}

		/* 2è¡Œç›®ä»¥é™�ã�®èª­ã�¿è¾¼ã�¿ */

		for (int i = 0; i < sizeY; i++) {
			line = rd.readLine();
			tokens = line.split(",");
			Y[i] = Double.valueOf(tokens[0]);

			// Zå€¤ã�®èª­ã�¿è¾¼ã�¿ã€�æ ¼ç´�
			for (int j = 0; j < sizeX; j++) {
				Z[i][j] = Double.valueOf(tokens[j + 1]);
			}
		}
		rd.close();
	}

}
