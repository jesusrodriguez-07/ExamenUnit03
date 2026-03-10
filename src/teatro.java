import java.util.Scanner;

public class teatro {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Declaracion del array sala
		char[][] sala = new char[6][10];
		int opcion = 0;

		// Inicializamos el array con todo a L
		for (int i = 0; i < sala.length; i++) {
			for (int j = 0; j < sala[i].length; j++) {
				sala[i][j] = 'L';
			}
		}

		// Hacemos un bucle do while donde mostrar una serie de prints para que se vean
		// las opciones del menu y un switch para elegir cual de todas quieres hacer
		try {
			do {
				System.out.println("1. Mostrar sala");
				System.out.println("2. Reservar asiento suelto");
				System.out.println("3. Reservar grupo en fila");
				System.out.println("4. Confirmar reservas (R --> O)");
				System.out.println("5. Cancelar reservar (R--> L");
				System.out.println("6. Estadísticas");
				System.out.println("7. Salir");

				opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					mostrarSala(sala);
					break;

				// Pedimos valores de fila y columna, comprobamos que son correctos y cambiamos
				// el valor de esa celda
				case 2:
					try {
						System.out.println("Introduce el número de fila");
						int fila = sc.nextInt();
						System.out.println("Introduce el número de columna");
						int columna = sc.nextInt();
						if (esPosicionValida(sala, fila, columna)) {
							reservarAsiento(sala, fila, columna);
						} else {
							System.out.println("Introduce unos valores dentro del tamaño de la sala");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				// Pedimos valores de fila y numero de personas y cambiamos los valores de las
				// celdas adecuadas
				case 3:
					try {
						System.out.println("Introduce el número de la fila");
						int fila2 = sc.nextInt();
						System.out.println("Introduce el número de personas");
						int numeroDePersonas = sc.nextInt();
						if (esPosicionValida(sala, fila2, numeroDePersonas)) {
							reservarGrupoEnFila(sala, fila2, numeroDePersonas);
						} else {
							System.out.println("Introduce unos valores dentro del tamaño de la sala");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				case 4:
					confirmarReservas(sala);
					break;

				case 5:
					cancelarReservas(sala);
					break;

				case 6:
					mostrarEstadisticas(sala);
					break;

				case 7:
					System.out.println("Saliendo...");
					break;

				default:
					System.out.println("Valor incorrecto");
				}
			} while (opcion != 7);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sc.close();
	}

	/**
	 * es una funcion de puro muestreo donde vamos a imprimir por pantalla el
	 * resultado de el array de sala
	 * 
	 * @param sala
	 */
	static void mostrarSala(char[][] sala) {
		for (int i = 0; i < sala.length; i++) {
			if (i >= 0) {
				System.out.print("fila: (" + (i + 1) + ") ");
			}
			for (int j = 0; j < sala[i].length; j++) {
				if (i == 0) {
					System.out.print("columna: (" + (j + 1) + ") " );
				}
				System.out.print(sala[i][j] + "	");
			}
			System.out.println();
		}
	}

	/**
	 * funcion para validar si la fila y columna indicada estan dentro de los
	 * valores de sala
	 * 
	 * @param sala
	 * @param fila
	 * @param columna
	 * @return
	 */
	static boolean esPosicionValida(char[][] sala, int fila, int columna) {
		if (fila < sala.length && fila >= 0 && columna < sala[fila].length && columna >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * funcion para contar cuantos asientos hay con cada estado que vamos a
	 * introducir por parametros
	 * 
	 * @param sala
	 * @param estado
	 * @return
	 */
	static int contarEstado(char[][] sala, char estado) {
		int contador = 0;

		for (int i = 0; i < sala.length; i++) {
			for (int j = 0; j < sala[i].length; j++) {
				if (sala[i][j] == estado) {
					contador++;
				}
			}
		}

		return contador;
	}

	/**
	 * Introducimos la fila y la columna sobre la que ver si esta libre y si se
	 * cambia devolvemos true, sino devolvemos false
	 * 
	 * @param sala
	 * @param fila
	 * @param columna
	 * @return
	 */
	static boolean reservarAsiento(char[][] sala, int fila, int columna) {
		if (esPosicionValida(sala, fila, columna)) {
			if (sala[fila][columna] == 'L') {
				sala[fila][columna] = 'R';
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Funcion para marcar una serie de posiciones como reservada segun el
	 * numeroDePersonas
	 * 
	 * @param sala
	 * @param fila
	 * @param numeroDePersonas
	 * @return
	 */
	static void reservarGrupoEnFila(char[][] sala, int fila, int numeroDePersonas) {
//		int[] numeroAsientos = {sala[fila][numeroDePersonas] };

		if (esPosicionValida(sala, fila, numeroDePersonas)) {
			for (int i = 0; i < numeroDePersonas; i++) {
				if (sala[fila][i] == 'L') {
					sala[fila][i] = 'R';
				}
			}
		}
	}

	/**
	 * Busca todos los valores puestos a R y los cambia a O
	 * 
	 * @param sala
	 */
	static void confirmarReservas(char[][] sala) {
		for (int i = 0; i < sala.length; i++) {
			for (int j = 0; j < sala[i].length; j++) {
				if (sala[i][j] == 'R') {
					sala[i][j] = 'O';
				}
			}
		}
	}

	/**
	 * Busca todas las ocurrencia iguales a R y las cambia por L de nuevo
	 * 
	 * @param sala
	 */
	static void cancelarReservas(char[][] sala) {
		for (int i = 0; i < sala.length; i++) {
			for (int j = 0; j < sala[i].length; j++) {
				if (sala[i][j] == 'R') {
					sala[i][j] = 'L';
				}
			}
		}
	}

	/**
	 * Recojemos datos de ocupacion de la tabla y los mostramos en forma de
	 * estadisticas
	 * 
	 * @param sala
	 */
	static void mostrarEstadisticas(char[][] sala) {
		int contadorL = 0;
		int contadorR = 0;
		int contadorO = 0;
		int total = 0;
		int ocupado = 0;
		int contadorOcupado = 0;
		int filaMasOcupada = 0;

		// Usamos la funcion para contar cuantas celdas hay en cada estado y las
		// guardamos en su contador unico
		contadorL = contarEstado(sala, 'L');
		contadorR = contarEstado(sala, 'R');
		contadorO = contarEstado(sala, 'O');

		// En cada iteracion del bucle externo pongo contador a 0 y dentro del bucle
		// interno cuento cuantas R o O hay en esa fila
		// Una vez se cuenta se compara con la actual fila mas ocupada y si es mayor se
		// actualiza a la nueva fila.
		for (int i = 0; i < sala.length; i++) {
			contadorOcupado = 0;
			for (int j = 0; j < sala[i].length; j++) {
				if (sala[i][j] == 'R' || sala[i][j] == 'O') {
					contadorOcupado++;
				}
			}
			if (contadorOcupado > filaMasOcupada) {
				filaMasOcupada = i;
			}
		}

		total = contadorL + contadorR + contadorO;
		ocupado = contadorR + contadorO;
		double porcentajeOcupacion = (ocupado / total) * 100;

		System.out.println("Número de asientos libres: " + contadorL);
		System.out.println("Número de asientos reservados: " + contadorR);
		System.out.println("Número de asientos ocupados: " + contadorO);
		System.out.println("Este es el porcentaje de ocupación de la sala: " + porcentajeOcupacion);
		System.out.println("Esta es la fila más ocupada: " + filaMasOcupada);
	}
}
