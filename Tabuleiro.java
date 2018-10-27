import java.util.*;

class Tabuleiro {
	int[][] board;
	int emptyX;
	int emptyY;

	//Metodos e Construtores

	//Construtor que aceita vetor
	Tabuleiro(int[] in) {
		this.board = new int[4][4];
		int k = 0;
		for (int i = 0; i < 4 ; i++)
			for (int j = 0 ; j < 4 ; j++) {
				this.board[i][j] = in[k];
				k++;
				if (this.board[i][j] == 0) {
					this.emptyX = i;
					this.emptyY = j;
				}
			}
				
	}

	//Construtor default
	Tabuleiro() {
		this.board = new int[4][4];
		int k = 1;
		for (int i = 0; i < 4 ; i++)
			for (int j = 0 ; j < 4 ; j++) {
				this.board[i][j] = k;
				k++;
			}
		this.board[3][3] = 0;
		this.emptyX = 3;
		this.emptyY = 3;
	}

	    //Construtor de copia teste -- NAO MEXER
    Tabuleiro(Tabuleiro t1) {
    	this.board = new int[4][4];
    	for (int i = 0 ; i < 4 ; i++){
    		for (int j = 0; j<4 ; j++) {
    			board[i][j] = t1.board[i][j];
    		}
    	}
    	this.emptyX = t1.emptyX;
    	this.emptyY = t1.emptyY;
    }
    
    
	//Imprime tabuleiro nao final
	void imprimirTabuleiro() {
		int k = 0 ;
		for (int i = 0; i < 4 ; i++)
			for (int j = 0 ; j < 4 ; j++) {
				System.out.printf("%d | ",this.board[i][j]);
				k++; if ( (k % 4) == 0) System.out.println();
			}
	}



	//Retorna um vetor com os movimentos possiveis
	char[] movesPossiveis() {
		char[] possiveis = new char[4];
		for (int i = 0 ; i < 4 ; i++)
			possiveis[i] = 'n';
		char[] movimentos = new char[4];
			movimentos[0] = 'u';
			movimentos[1] = 'd';
			movimentos[2] = 'l';
			movimentos[3] = 'r';
		int j= 0;
		for (int i=0 ; i < 4 ; i++) {
			if (movimentos[i] == 'u' && this.emptyX==0)
				continue;
			else if (movimentos[i] == 'd' && this.emptyX==3)
				continue;
			else if (movimentos[i] == 'l' && this.emptyY==0)
				continue;
			else if (movimentos[i] == 'r' && this.emptyY==3)
				continue;
			possiveis[j] = movimentos[i];
			j++;
		}
		return possiveis;
	}


	//Altera o tabuleiro (as coordenadas se calhar estao trocadas)
	void move(char movimento){
		 switch(movimento){
        case 'u':
  
            this.board[emptyX][emptyY] = this.board[emptyX-1][emptyY];
            this.board[emptyX-1][emptyY] = 0;
            this.emptyX-=1;
            break;
        case 'd':
            this.board[emptyX][emptyY] = this.board[emptyX+1][emptyY];
            this.board[emptyX+1][emptyY] = 0;
            this.emptyX+=1;
            break;
        case 'l':
            this.board[emptyX][emptyY] = this.board[emptyX][emptyY-1];
            this.board[emptyX][emptyY-1] = 0;
            this.emptyY-=1;
            break;
        case 'r':
            this.board[emptyX][emptyY] = this.board[emptyX][emptyY+1];
            this.board[emptyX][emptyY+1] = 0;
            this.emptyY+=1;
            break;
  	  }
	}
public String toString() {
	String str = "";
	for (int i = 0 ; i < 4 ; i++)
		for (int j = 0 ; j < 4; j++)
			str+=this.board[i][j];
	return str; 
}

public boolean equals(Tabuleiro b) {
        for (int i = 0 ; i < 4 ; i++)
        	for (int j = 0 ; j < 4 ; j++)
        		if (board[i][j] != b.board[i][j])
        			return false;
    	return true;
    }

//hash function
    	@Override
        public int hashCode(){
        return	this.toString().hashCode();


       /* int hash=0;
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++)
                    hash = (hash * 256 + this.board[i][j]) % 16;
            }

            return hash;*/
        }
    


}
