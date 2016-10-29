import java.util.Scanner;

public class Main {
	public static int INF = Integer.MAX_VALUE;
	public static Scanner scanner = new Scanner( System.in );

	public static void main(String[] args) {
		int number_nodes = getIntFromConsole("Numero di nodi: ");
		int[][] connections = generateConnections(number_nodes);
		int center = getIntFromConsole("Nodo di riferimento: ");
		int[][] connections_new = findShortestConnections(center, connections);
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

	public static int[][] generateConnections(int number_nodes) {
		int[][] connections = new int[number_nodes][number_nodes];

		for(int i=0; i<connections.length; i++) {
    		for(int j=i+1; j<connections.length; j++) {
        		int dist = getIntFromConsole("Distanza tra i nodi ["+(i+1)+"]-["+(j+1)+"] (- se non sono connessi): ");
        		if(dist <= -1) dist = INF;
        		connections[i][j] = dist;
        		connections[j][i] = dist;
    		}
		}

		return connections;
	}

	public static int[][] findShortestConnections(int center, int[][] connections) {
		int l = connections.length;

		int[] seq = new int[l];
		seq[0] = center;

		int[][] connections_new = connections;

		

		return connections_new;
	}

	public static algo(int[] seq, )
}