import java.util.*;
import java.lang.*;

class Heuristicas {
	//flag para escolher heuristica ou heuristica + depth
	static void introHeuristica(Node no, Tabuleiro obj, int opcao) {
		if (opcao == 1) //aplica heuristica 1  //1 ou 2 para gulosa
			no.cost = heuristica1(no,obj);
			
		else if (opcao == 2) // aplica heuristica 2
			no.cost = heuristica2(no,obj);
		else if (opcao == 3)//aplica heuristica 1 + profundiade //3 ou 4 para astar
			no.cost = heuristica1(no,obj) + no.depth;
		
		else //aplica heuristica 2 + profundidade
			no.cost = heuristica2(no,obj) + no.depth;
		return;
	}
	// numero de peças fora de sitio
	static int heuristica1(Node no, Tabuleiro objetivo) {
		int fora = 0;
		for (int i = 0 ; i < 4 ; i++) {
			for (int j = 0 ; j < 4 ; j++) {
				if (no.tab.board[i][j] != objetivo.board[i][j])
					fora++;
			}
		}
		return fora;
	}

	//manhattan distance
	static int heuristica2(Node no, Tabuleiro objetivo) {
		int peca = 0;
		int distancia = 0;
		int atual = 0;
		int[] fin = new int[2];
		for (int i = 0 ; i < 4 ; i++) {
			for (int j = 0 ; j < 4 ; j++) {
				atual = no.tab.board[i][j];
				fin = auxiliar(objetivo,atual);
				peca++;
				if (atual != 0 && atual != peca)
					distancia += Math.abs((int) i - fin[0]) + Math.abs((int)j - fin[1]);
			}
		}
		return distancia;
	}

	static int[] auxiliar(Tabuleiro objetivo, int valor) {
	    int[] pos = new int[2];
	    for(int i = 0; i < 4; i++) {
	        for(int j = 0; j < 4; j++){
	            if(objetivo.board[i][j] == valor){
	                pos[0] = i;
	                pos[1] = j;
	                return pos;
	            }
	        }
	    }
	    return pos;
	}
}