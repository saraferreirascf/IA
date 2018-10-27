import java.util.*;

class main {
    
    public static int lado=4;
    
    public static void main(String args[])
    	{
	Scanner stdin = new Scanner(System.in);
	
	System.out.println();
	System.out.println("---------------------------------");
	System.out.println("|| Bem-vindo ao jogo dos 15 (: ||");
	System.out.println("---------------------------------");
	System.out.println();
	
	System.out.println("Insira a configuração inicial: ");

int b=0;
int[] input=new int[16];

int[][] confinicial = new int [lado][lado];
	
	for(int i=0;i<lado;i++) {
	    for(int j=0; j< lado;j++) {
		confinicial[i][j]=stdin.nextInt();
		}
	}

for(int i=0;i<lado;i++) {
	    for(int j=0; j< lado;j++) {
		input[b++]=confinicial[i][j];
		
	    }
	}
if(solvability(confinicial)==0) {
	    System.out.println("Configuraçao não válida!");
	    return; 
	}

Tabuleiro inicial = new Tabuleiro(input);
	
 System.out.println("Insira a configuração final:");

 int s=0;
int[] output=new int[16];

int[][] conffinal = new int [lado][lado];
	for(int i=0;i<lado;i++) {
	    for(int j=0; j< lado;j++) {
		conffinal[i][j]=stdin.nextInt();
		}
	}

for(int i=0;i<lado;i++) {
	    for(int j=0; j< lado;j++) {
		output[s++]=conffinal[i][j];
		}
	}

if(solvability(conffinal)==0) {
	    System.out.println("Configuraçao não válida!");
	    return; }

 Tabuleiro objetivo = new Tabuleiro(output);

	
	//escolha da busca

	System.out.println(" -----------------------------------");
	System.out.println(" |Escolha o tipo de busca a efetuar|");
	System.out.println(" -----------------------------------");
	System.out.println();
	System.out.println("|1|- Busca A*");
	System.out.println("|2|- Busca gulosa :p");
	System.out.println("|3|- BFS-> Busca em largura");
	System.out.println("|4|- DFS-> Busca em profundidade");
	System.out.println("|5|- IDFS-> Busca em profundidade(com profundidade máxima a dar como argumento");
	System.out.println();
	System.out.print("Escolha: ");

	int escolha=stdin.nextInt();
	
	switch(escolha) {
	case 1:
	 	
	 	System.out.println("Que heuristica pretende usar?");
	 	System.out.println();
	 	System.out.println("|3|- Contagem de peças fora do lugar");	
	 	System.out.println("|4|- Manhattan distance");
	 	System.out.println();
	 	System.out.print("Escolha: ");
	 	int opcao1=stdin.nextInt();
	 	Search.generalSearchASTAR(inicial,objetivo,opcao1);
	 	break;
	case 2:
		 
		System.out.println("Que heuristica pretende usar?");
	 	System.out.println();
	 	System.out.println("|1|- Contagem de peças fora do lugar");	
	 	System.out.println("|2|- Manhattan distance");
	 	System.out.println();
	 	System.out.print("Escolha: ");
	 	int opcao2=stdin.nextInt();
	 	Search.generalSearchGULOSA(inicial,objetivo,opcao2);
	    break;
	case 3:
	  	Search.generalSearchBFS(inicial, objetivo);
	    break;
	case 4:
	    Search.generalSearchDFS(inicial, objetivo);
	    break;
	case 5:
	    System.out.println("Insira a profundidade máxima pretendida: ");
	    int p=stdin.nextInt();
	    Search.generalSearchIDFS(inicial, objetivo, p);
	    break;
	}

    }

    public static int solvability(int[][] confinicial) {

	int[] v=new int[lado*lado];
	int l=0;
	int linha0=0;
	for(int i=0;i<lado;i++) { //passa matriz para arrays
	    for(int j=0; j< lado;j++) {
		if(confinicial[i][j]==0)
		    linha0=i;
		v[l++]=confinicial[i][j];
	    }
	}
	int inv=0;
	
	v=remove(0,v); //remove o 0 do array

	for(int i=0;i<15;i++) { //inversoes possiveis no array
	    for(int j=i+1;j<15;j++) {
		if(v[i]>v[j])
		    inv++;
	    }
	}

	if( ( (linha0==0 || linha0==2) && (inv%2!=0) ) || ( ( (linha0==1) || linha0==3) && (inv%2==0) ) )
	    return 1;
	else
	    return 0;
	
    }
    
    public static int[] remove(int y,int[] v){
	int j = 0;
	for(int i = 0; i < 16; i++){
	    if(v[i] != y){
		j++;
	    }
	}
	int[] vetorAux = new int[j];
	int index = 0;
	for(int i = 0; i < 16; i++){
	    if(v[i] != y){
		vetorAux[index] = v[i];
		index++;
	    }
	}
	v = vetorAux;
	return v;
    }

}
