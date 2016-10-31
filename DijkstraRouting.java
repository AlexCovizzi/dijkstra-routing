import java.util.Scanner;
import java.util.Arrays;

public class DijkstraRouting {
	public static int INF = 20000000;
	public static Scanner scanner = new Scanner( System.in );
	public static final int[][] TEST = {{0, 2, INF, 1, 5, INF},
										{2, 0, 4, INF, 3, INF},
										{INF, 4, 0, INF, 3, 2},
										{1, INF, INF, 0, 2, 2},
										{5, 3, 3, 2, 0, INF},
										{INF, INF, 2, 2, INF, 0}};

	public static void main(String[] args) {
		int number_nodes = -1;
		int[][] connections;

		System.out.println("Usare la rete test? [s/n]:");
		String s = scanner.nextLine();

		if(s.equals("s")) {
			number_nodes = TEST.length;
			connections = TEST;
		}else{
			while(number_nodes < 2) {
				number_nodes = getIntFromConsole("Numero di nodi (deve essere >= 2): ");
			}
			connections = generateConnections(number_nodes);
		}
		System.out.print("\n");
		for(int i=0; i<connections.length; i++) System.out.println(arrToString(connections[i], false));
		System.out.print("\n");

		int center = -1;
		while(center < 1 || center > number_nodes) {
			center = getIntFromConsole("Nodo di riferimento (deve essere compreso tra 1 e "+number_nodes+"): ");
			System.out.print("\n");
		}
		center--;

		int[][] connections_new = findShortestRoutes(center, connections);
		for(int i=0; i<connections_new.length; i++) System.out.println(arrToString(connections_new[i], false));
	}

	public static int[][] generateConnections(int number_nodes) {
		int[][] connections = new int[number_nodes][number_nodes];

		for(int i=0; i<connections.length; i++) {
			for(int j=i+1; j<connections.length; j++) {
				int dist = -1;
				while(dist <= 0) {
					dist = getIntFromConsole("Distanza tra i nodi ("+intToString(i, true)+")-("+intToString(j, true)+")"+
						" (- se non sono connessi): ");
				}
				connections[i][j] = dist;
				connections[j][i] = dist;
			}
		}

		return connections;
	}

	public static int[][] findShortestRoutes(int center, int[][] connections) {
		int l = connections.length;

		int[][] connections_new = new int[l][l];
		for(int i=0; i<l; i++) {
			Arrays.fill(connections_new[i], INF);
			connections_new[i][i] = 0;
		}

		int[] connections_aux = new int[l];
		int[] dists_to_center = connections[center];

		int[] seq = new int[l];
		Arrays.fill(seq, INF);
		seq[0] = center;

		System.out.print("F = "+arrToString(seq, true)+" | ");
		System.out.print(arrToString(dists_to_center, false));
		System.out.print("\n");

		for(int i=0; i<l-1; i++) {

			int min_dist = INF;
			int node_min_dist = -1;

			for(int j=0; j<l; j++) {
				if(!isInArray(j, seq)) {
					if(dists_to_center[j] < min_dist) {
						node_min_dist = j;
						min_dist = dists_to_center[j];
					}
				}
			}

			seq[i+1] = node_min_dist;

			for(int j=0; j<l; j++) {
				if(!isInArray(j, seq)) {
					if(dists_to_center[j] > connections[seq[i+1]][j]+dists_to_center[seq[i+1]]) {
						dists_to_center[j] = connections[seq[i+1]][j]+dists_to_center[seq[i+1]];
						connections_aux[j] = seq[i+1];
					}else{
						
					}
				}else{
					
				}
			}
			System.out.print("F = "+arrToString(seq, true)+" | ");
			System.out.print(arrToString(dists_to_center, false));
			System.out.print("\n");
		}
		System.out.print("\n");

		for(int i=0; i<l; i++) {
			if(i > connections_aux[i]) connections_new[connections_aux[i]][i] = connections[connections_aux[i]][i];
			else connections_new[i][connections_aux[i]] = connections[i][connections_aux[i]];
		}

		for(int i=0; i<l; i++) {
			for(int j=i+1; j<l; j++) {
				connections_new[j][i] = connections_new[i][j];
			}
		}

		return connections_new;
	}

	public static int getIntFromConsole(String message) {
		System.out.println(message);
		String s = scanner.nextLine();
		int n = -1;
		if(s.equals("-")) n = INF;
		else{
			try {
				n = Integer.parseInt(s);
			}catch(NumberFormatException e) {
				System.err.println(e);
				System.exit(1);
			}
		}
		return n;
	}

	public static String arrToString(int[] arr, boolean isNode) {
		String string = "";
		string += "[";
		for(int i=0; i<arr.length; i++) {
			string += intToString(arr[i], isNode);
			if(i<arr.length-1) string += ", ";
		}
		string += "]";

		return string;
	}

	public static String intToString(int i, boolean isNode) {
		if(i >= INF) {
			return "--";
		}else{
			int n;
			if(isNode) n = (i+1);
			else n = i;

			if(n >= 10) return ""+n;
			else return n+" ";
		}
	}

	public static boolean isInArray(int n, int[] arr) {
		for(int i=0; i<arr.length; i++) {
			if(n == arr[i])
				return true;
		}
		return false;
	}
}
