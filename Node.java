import java.util.*;


class Node implements Comparable<Node> {
    
    Tabuleiro tab;
    char move; //movimento que o no originou
    int depth; //profundidade
    Node pai;
    int cost;
    Node[] filhos; //nós descendentes

    //Construtores
    Node() {
        this.tab = null;
        this.move = 'n'; //null
        this.depth = 0;
        this.cost = 0;
        this.pai = null;
        this.filhos = new Node[4];
    }

    Node(Tabuleiro inicial) {
    	this.tab = inicial;
    	this.move = 'n'; //null
    	this.depth = 0;
    	this.pai = null;
        this.filhos = new Node[4];
        this.cost = 0;
    }

    //Construtor especial para o fazerFilhos
    Node(Tabuleiro tabX,char movement, Node pai) {
    	tab = tabX;
    	tab.move(movement);
    	this.move = movement;
    	this.pai = pai;
        this.filhos = new Node[4];
    	this.cost = 0;
        this.depth= pai.depth+1; 
    }

      Node(Node nd) {
        this.tab = nd.tab;
        this.move = nd.move;
        this.pai = nd.pai;
        this.filhos = nd.filhos;
    	this.cost = 0;
    }


    Node[] fazerFilhos(HashSet hmap) {
    	
    	char[] possiveis = new char[4];
        possiveis = this.tab.movesPossiveis();
        //System.out.println(possiveis[0] + " "+ possiveis[1]);
        Tabuleiro[] vecTab = new Tabuleiro[4];
        

	//	Tabuleiro tabX = new Tabuleiro(this.tab);
	//	tabX.imprimirTabuleiro();
		Node[] filhosFila = new Node[4];
		Node[] children = new Node[4];
        int j = 0;
 		for (int i = 0 ; i < 4 && possiveis[i] != 'n' ; i++) {
 			vecTab[i] = new Tabuleiro(tab);
            if(hmap.contains(vecTab[i]) == true) {
                System.out.println("poia");
                continue;
            } 
            else{   
                hmap.add(vecTab[i]);         
                children[j] = new Node(vecTab[i],possiveis[i],this);
                filhosFila[j] = children[j];
                j++;
            }
            //children[i].tab.imprimirTabuleiro();
 			//System.out.println("Letras: " + possiveis[i]);
 			
        }
 		        
		return filhosFila;	
    }

     Node[] fazerFilhos() {
        
        char[] possiveis = new char[4];
        possiveis = this.tab.movesPossiveis();
        /*int tmp=0;
        for(int i=0;i<possiveis.length;i++ ){
            if(possiveis[i]!='n')
                tmp++;
        }       
        System.out.println(tmp);*/
        Tabuleiro[] vecTab = new Tabuleiro[4];
        

    //  Tabuleiro tabX = new Tabuleiro(this.tab);
    //  tabX.imprimirTabuleiro();
        Node[] filhosFila = new Node[4];
        Node[] children = new Node[4];
        for (int i = 0 ; i < 4 && possiveis[i] != 'n' ; i++) {
            vecTab[i] = new Tabuleiro(tab);
           children[i] = new Node(vecTab[i],possiveis[i],this);
            //children[i].tab.imprimirTabuleiro();
            //System.out.println("Letras: " + possiveis[i]);
            filhosFila[i] = children[i];
        }
                
        return filhosFila;  
    }

    static char imprimirCaminho(Node nd) {
    	if (nd.move == 'n')
    		return 'n';
    	else {
    		System.out.print(nd.move + " > ");
    		nd = nd.pai;
    		return imprimirCaminho(nd);
    	}
    }

       public int compareTo(Node b) {
            if (this.cost < b.cost)
                return -1;
            else if(this.cost == b.cost)
                return 0;
            return 1;
        }
    }

  /*  public int compareTo(Node b) {
        for(int i=0; i<4;i++) {
            for(int j=0; j<4;j++) {
                if(this.tab.board[i][j]!=b.tab.board[i][j])
                    return 1;
            }
        }
        return 0;
    }*/


