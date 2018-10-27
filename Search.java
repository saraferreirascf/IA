import java.util.*;

class Search {
//hash map no bfs,dfs e gulosa
	public static void generalSearchBFS(Tabuleiro ini, Tabuleiro objetivo) {
   	long start = System.currentTimeMillis(); //para aparecer em segundos
	Node inicial = new Node(ini); //no inicial
	Queue<Node> fila = new LinkedList<Node>(); //fila de nodes


	HashSet<Tabuleiro> hmap = new HashSet<Tabuleiro>();
	hmap.add(ini);


	int nosVisitados=0,nosGerados=0;
	fila.offer(inicial); //inserir no inicial na fila
	++nosGerados;
	int depth=0;
	int num = 1;
	while (fila.peek() != null) {

	    Node avaliar=fila.poll(); //primeiro elemento
	    //fila.poll(); //pop
	    ++nosVisitados;

	    if( avaliar.tab.equals(objetivo) ) {
	    	long tempoFinal= (long)(System.currentTimeMillis());
	    	System.out.printf("Tempo decorrido: %.3f s%n", (tempoFinal - start) / 1000d);	
	    	System.out.println("Encontramos a soluçao!");
	    	System.out.println("A solução está num nivel de profundidade "+(avaliar.depth-1));
			Node.imprimirCaminho(avaliar);
	    	System.out.println("Número de nós gerados: "+nosGerados+"\nNúmero de nós visitados: "+nosVisitados);
	    	return;
	    }
	    //System.out.println(avaliar.tab.movesPossiveis().length);
	    Node nosFilhos[] = new Node[avaliar.tab.movesPossiveis().length];
	    nosFilhos = avaliar.fazerFilhos(hmap);

			
			if(avaliar.depth > depth){
				System.out.println("depth: " + depth);
				depth = avaliar.depth;
			}
	    for(int i=0; i < avaliar.tab.movesPossiveis().length; i++) {
	    	//não esta a inserir filhos
	    	if(nosFilhos[i]!=null)
			fila.add(nosFilhos[i]); //push
			++nosGerados;
		}
		//System.out.println(nosFilhos.length + " o ");
		num++;
		//System.out.println(fila.size());

	}
	System.out.println("Não foi encontrada nenuma solução!");
}

public static void generalSearchDFS(Tabuleiro ini, Tabuleiro objetivo) {
	long start = System.currentTimeMillis(); 
	int nosGerados=0, nosVisitados=0;
	Node inicial = new Node(ini); //no inicial
	Stack<Node> stk = new Stack<Node>(); //fila de nodes

	stk.push(inicial); //inserir no inicial na fila
	++nosGerados;
	int depth=0;
	int num = 1;
	while (stk.empty() == false) {

	    Node avaliar=stk.peek(); //primeiro elemento
	   
	    stk.pop(); //pop
	   ++nosVisitados;


	    if( avaliar.tab.equals(objetivo) ) {
	    	long tempoFinal= (long)(System.currentTimeMillis());
	    	System.out.printf("Tempo decorrido: %.3f s%n", (tempoFinal - start) / 1000d);	
	    	
	    	System.out.println("Encontramos a soluçao!");
	    	System.out.println("A solução está num nivel de profundidade "+depth);
	    	System.out.println("Número de nós gerados: "+nosGerados+"\nNúmero de nós visitados: "+nosVisitados);
	    	Node.imprimirCaminho(avaliar);
	    	return;
	    }
 	
 	   if(avaliar.depth >= 80)
	    		continue;


	    	Node nosFilhos[] = new Node[4];
	    	nosFilhos = avaliar.fazerFilhos();
	    	depth=nosFilhos[0].depth; 
	    	

	    	for(int i=0; i < 4 && nosFilhos[i] != null; i++) {
	    	//não esta a inserir filhos
			stk.push(nosFilhos[i]); //push
			++nosGerados;
		}
	}
	num++;

System.out.println("Não foi encontrada nenuma solução!");
}


public static void generalSearchGULOSA(Tabuleiro ini, Tabuleiro objetivo, int opcao) {
	long start = System.currentTimeMillis(); 
	Node inicial = new Node(ini); //no inicial
	PriorityQueue<Node> pfila = new PriorityQueue<Node>();

	

	int nosGerados=0, nosVisitados=0;


	pfila.offer(inicial); //inserir no inicial na fila
	++nosGerados;
	int depth=0;		
	int num = 1;
	while (pfila.peek() != null) {

	    Node avaliar=pfila.peek(); //primeiro elemento
	    pfila.remove(); //pop
	    ++nosVisitados;

	    if( avaliar.tab.equals(objetivo) ) {
	    	long tempoFinal= (long)(System.currentTimeMillis());
	    	System.out.printf("Tempo decorrido: %.3f s%n", (tempoFinal - start) / 1000d);	
	    	System.out.println("Encontramos a soluçao!");
	    	Node.imprimirCaminho(avaliar);
	    	System.out.println("A solução está num nivel de profundidade "+depth);
	    	System.out.println("Número de nós gerados: "+nosGerados+"\nNúmero de nós visitados: "+nosVisitados);
	    	return;
	    }
	    
	    Node nosFilhos[] = new Node[4];
	    nosFilhos = avaliar.fazerFilhos();
	    depth=nosFilhos[0].depth; 

	    for(int i=0; i < 4 && nosFilhos[i] != null; i++) {
	    	Heuristicas.introHeuristica(nosFilhos[i],objetivo,opcao);
			pfila.offer(nosFilhos[i]); //push
	    	
			++nosGerados;
		}
		num++;
	}
	System.out.println("Não foi encontrada nenuma solução!");
}

public static void generalSearchIDFS(Tabuleiro ini, Tabuleiro objetivo, int profundidade) {
	long start = System.currentTimeMillis(); 
	int nosGerados=0, nosVisitados=0;
	Node inicial = new Node(ini); //no inicial
	Stack<Node> stk = new Stack<Node>(); //fila de nodes

	stk.push(inicial); //inserir no inicial na fila
	++nosGerados;
	int depth=0;
	int num = 1;
	while (stk.empty() == false) {

	    Node avaliar=stk.peek(); //primeiro elemento
	   
	    stk.pop(); //pop

	    ++nosVisitados;

	    if( avaliar.tab.equals(objetivo) ) {
	    	long tempoFinal= (long)(System.currentTimeMillis());
	    	System.out.printf("Tempo decorrido: %.3f s%n", (tempoFinal - start) / 1000d);	
	    	
	    	System.out.println("Encontramos a soluçao!");
	    	System.out.println("A solução está num nivel de profundidade "+depth);
	    	System.out.println("Número de nós gerados: "+nosGerados+"\nNúmero de nós visitados: "+nosVisitados);
	    	Node.imprimirCaminho(avaliar);
	    	return;
	    }
	     	if(avaliar.depth < profundidade) {	
	    	Node nosFilhos[] = new Node[4];
	    	nosFilhos = avaliar.fazerFilhos();
	    	depth=nosFilhos[0].depth; 

	    	for(int i=0; i < 4 && nosFilhos[i] != null; i++) {
	    	//não esta a inserir filhos
			stk.push(nosFilhos[i]); //push
			++nosGerados;
		}
	}
	num++;
	}

System.out.println("Não foi encontrada nenuma solução!");
}

public static void generalSearchASTAR(Tabuleiro ini, Tabuleiro objetivo, int opcao) {
	long start = System.currentTimeMillis(); 
	Node inicial = new Node(ini); //no inicial
	PriorityQueue<Node> pfila = new PriorityQueue<Node>();

	int nosGerados=0;int nosVisitados=0;

	pfila.offer(inicial); //inserir no inicial na fila
	++nosGerados;
	int depth=0;		
	int num = 1;
	while (pfila.peek() != null) {
		
	    Node avaliar=pfila.peek(); //primeiro elemento
	    pfila.remove(); //pop
	    ++nosVisitados;

	    if( avaliar.tab.equals(objetivo) ) {
	    	long tempoFinal= (long)(System.currentTimeMillis());
	    	System.out.printf("Tempo decorrido: %.3f s%n", (tempoFinal - start) / 1000d);	
	    	System.out.println("Encontramos a soluçao!");
	    	Node.imprimirCaminho(avaliar);
	    	System.out.println("A solução está num nivel de profundidade "+depth);
	    	System.out.println("Número de nós gerados: "+nosGerados+"\nNúmero de nós visitados: "+nosVisitados);
	    	return;
	    }
	    
	    Node nosFilhos[] = new Node[4];
	    nosFilhos = avaliar.fazerFilhos();
	    depth=nosFilhos[0].depth; 

	    for(int i=0; i < 4 && nosFilhos[i] != null; i++) {
	    	Heuristicas.introHeuristica(nosFilhos[i],objetivo,opcao);
			pfila.offer(nosFilhos[i]); //push
			++nosGerados;
		}
		num++;
	}
	System.out.println("Não foi encontrada nenhuma solução!");
}
}
	