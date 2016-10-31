import java.util.Scanner;
import java.util.Arrays;

public class Main {
	public static int INF = 20000000;
	public static Scanner scanner = new Scanner( System.in );

	public static void main(String[] args) {
		int number_nodes = -1;
		while(number_nodes < 2) {
			number_nodes = getIntFromConsole("Numero di nodi (deve essere >= 2): ");
		}

		int[][] connections = generateConnections(number_nodes);
		for(int i=0; i<connections.length; i++) System.out.println(arrToString(connections[i], false));

		int center = -1;
		while(center < 1 || center > number_nodes) {
			center = getIntFromConsole("Nodo di riferimento (deve essere compreso tra 1 e "+number_nodes+"): ");
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
		//System.out.print("F = "+arrToString(seq, true)+" | ");

		for(int i=0; i<l-1; i++) {
			int min_dist = INF;
			int node_min_dist = -1;

			for(int j=0; j<l; j++) {
				if(!isInArray(j, seq)) {
					//System.out.print(intToString(connections[center][j], false) + "  ");
					if(dists_to_center[j] < min_dist) {
						node_min_dist = j;
						min_dist = dists_to_center[j];
					}
				}else{
					//System.out.print("//  ");
				}
			}

			seq[i+1] = node_min_dist;

			for(int j=0; j<l; j++) {
				if(!isInArray(j, seq)) {
					if(dists_to_center[j] > connections[i+1][j]+dists_to_center[i+1]) {
						dists_to_center[j] = connections[i+1][j]+dists_to_center[i+1];
						connections_aux[j] = i+1;
					}else{
						
					}
				}else{
					
				}
			}
		}

		for(int i=0; i<l; i++) {
			connections_new[connections_aux[i]][i] = connections[connections_aux[i]][i];
		}

		for(int i=0; i<l; i++) {
			for(int j=i+1; j<l; j++) {
				connections_new[j][i] = connections_new[i][j];
			}
		}

		//System.out.println(arrToString(connections_aux, true));

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
			return "-";
		}else{
			if(isNode) return ""+(i+1);
			else return ""+i;
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