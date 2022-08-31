//CÁLCULO DE PUNTUACIÓN ESTÁNDAR
//Descripción: se calcula el valor de puntuación estándar de un arreglo con los datos ingresados por usuario.
//Desarrollador: Julio César Cárdenas Alvarado.
//Institución: CUCEA MTI
//Realización: 24/09/2021

import java.util.Scanner;
import java.util.InputMismatchException;

public class PuntuacionEstandar {
	public static void main (String args []) {
		Scanner dataIn = new Scanner(System.in);
		float sum = 0;
		float media = 0;
		float temporal = 0;
		double standardDev = 0;
		int size = 0;
		boolean repeat = false;
		boolean repeatB = false;
		char option;
		System.out.println("Calcule la puntuación estándar de un grupo de valores");
		
		do {
			do {
				try {
					System.out.print("Ingrese la cantidad de valores a calcular (elija un valor mayor a 3 y menor o igual a 15): ");
					size = dataIn.nextInt();
					repeat = false;
				}
				
				catch (InputMismatchException e) {
					System.err.println("Valor incorrecto. Introduzca un número entero.");
					dataIn = new Scanner( System.in );
					repeat = true;
				}
			}
			while (repeat == true);
			
			if (size > 3 && size <= 15) {
				float data [] = new float [size];
				double standardScore [] = new double[size];
				System.out.println("Ingrese los valores para calcular la desviación estándar (elija valores entre 5 y 300): ");
				vectorData(size, data, dataIn, repeat);
				sum = totalSum(data, sum);
				media = calcMedia(sum, size, media);
				standardDev = calcDev(data, media, standardDev);
				calcStandardScore(data, media, standardDev, standardScore);
				System.out.println("\nResultado:");
				printStandardScore(data, standardScore);
			}
			else {
				System.out.println("Valor incorrecto, ingrese un valor mayor que 3 e igual o menor a 15");
				repeat = true;
			}
			
			do {
				System.out.println("¿Desea hacer un nuevo cálculo? s/n");
				option = dataIn.next().charAt(0);
				if (option == 's') {
					System.out.println();
					repeat = true;
					repeatB = false;
					break;
				}
				else if (option == 'n') {
					System.out.println("Programa terminado. ¡Hasta luego!");
					repeat = false;
					repeatB = false;
					break;
				}
				
				else {
					System.out.println("Opción incorrecta");
					repeatB = true;
					break;
				}
			}
			while (repeatB == true);
		}
		
		while (repeat == true);
	}
	
	public static void vectorData (int size, float data[], Scanner dataIn, boolean repeat) {
		for (int i = 0; i < size; i++) {
			do {
				do {
					try {
						System.out.print("Ingrese el valor " + (i + 1) + ": ");
						data [i] = dataIn.nextFloat();
						repeat = false;
					}
					catch (InputMismatchException e) {
						System.err.println("Valor incorrecto. Introduzca un número.");
						dataIn = new Scanner( System.in );
						repeat = true;
					}
				}
				while (repeat == true);
				
				if (data[i] >= 5 && data[i] <= 300) {
					repeat = false;
					break;
				}
				else {
					System.out.println("Valor incorrecto, ingrese un número entre 5 y 300");
					repeat = true;
				}
			}
			while (repeat = true);
		}
	}
	
	public static void printVector(float data [], int size) {
		System.out.print("Los datos ingresados son: ");
		
		for (int i = 0; i < size; i++) {
			System.out.print(data[i] + " ");
		}
		
		System.out.println();
	}
	
	public static float totalSum (float data [], float sum) {
		sum = 0;
		
		for (int i = 0; i < data.length ; i++) {
			sum = sum + data[i];
		}
		
		return sum;
	}
	
	public static float calcMedia(float sum, int size, float media) {
		media = sum / size;
		return media;
	}
	
	public static double calcDev(float data [], float media, double standardDev) {
		double sum = 0;
		float temporal = 0;
		
		for (int i = 0; i < data.length; i++) {
			temporal = data[i] - media;
			sum = sum + Math.pow(temporal, 2);
		}
		
		double temporal2 = sum / (data.length - 1);
		standardDev = Math.sqrt(temporal2);
		return standardDev;
	}
	
	public static void calcStandardScore(float data [], float media, double standardDev, double standardScore []) {
		for (int i = 0; i < data.length; i++) {
			standardScore[i] = (data[i] - media) / standardDev;
		}
	}
	
	public static void printStandardScore(float data [], double standardScore []) {
		for (int i = 0; i < data.length; i++) {
			System.out.println("Valor " + (i+1) + ": " + data[i] + " - Puntuación Estándar: " + standardScore[i]);
		}
		System.out.println();
	}
}