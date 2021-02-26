package unam.ciencias.algoritmos.practica6;
import java.util.NoSuchElementException;

/**
*Implementación de colas binomiales.
*/
 public final class ColaBinomial<T extends Comparable<? super T>>{
  private static class Nodo<T>{
    T element;
	   Nodo<T> sonLeft;
	   Nodo<T> sonRight;

	/**
	 * Constructor del nodo
	 *
	 */
	Nodo(T element){
	    this(element, null, null);
	}

	/**
	 * Constructor del nodo
	 * 
	 */
	Nodo(T element, Nodo<T> sonLeft, Nodo<T> sonRight){
	    this.element = element;
	    this.sonLeft = sonLeft;
	    this.sonRight = sonRight;
	}
    }

    private static final int DEFAULT_TREES = 1;
    private int size;
    private Nodo<T>[] tree;

    /**
     * Contructor
     */
    @SuppressWarnings("unchecked")
    public ColaBinomial(){
	     tree = new Nodo[DEFAULT_TREES];
	     clean();
    }

    /**
     * Constructor con un solo nodo
     * 
     */
    @SuppressWarnings("unchecked")
    public ColaBinomial(T elemento){
	     size = 1;
	     tree = new Nodo[1];
	     tree[0] = new Nodo<>(elemento, null, null);
    }

    @SuppressWarnings("unchecked")
    private void expandTree(int numArboles){
	     Nodo<T>[] old = tree;
	     int numOld = tree.length;
	     tree = new Nodo[numArboles];
      System.arraycopy(old, 0, tree, 0, numOld);
	      for(int i = numOld; i < numArboles; i++)
	       tree[i] = null;
    }
 /**
     * Índica si la cola es vacía
     */
    public boolean isEmpty(){
	return size == 0;
    }
    /**
     * Ordena la cola binomial en la cola de prioridades
     * 
     */
    public void merge(ColaBinomial<T> tail){
	     if(this == tail)
	      return;
	       size += tail.size;
	        if(size > capacity()){
	           int numNew = Math.max(tree.length, tail.tree.length) + 1;
	            expandTree(numNew);
	  }
	   Nodo<T> n = null;
	    for(int i = 0, j = 1; j <= size; i++, j*=2){
	       Nodo<T> t1 = tree[i];
	       Nodo<T> t2 = i < tail.tree.length ? tail.tree[i]: null;
	       int c = t1 == null ? 0: 1;
	       c += t2 == null ? 0: 2;
	       c += n == null ? 0: 4;
	    switch(c){
	       case 0:
            case 1:
		   break;
	    case 2:
		     tree[i] = t2;
		     tail.tree[i] = null;
		    break;
	    case 4:
		      tree[i] = n;
		        n = null;
                    break;
	    case 3:
		      n = mergeArbol(t1, t2);
		      tree[i] = tail.tree[i] = null;
		    break;
	    case 5:
		     n = mergeArbol(t1, n);
		     tree[i] = null;
		     break;
            case 6:
		       n = mergeArbol(t2, n);
		       tail.tree[i] = null;
		     break;
	    case 7:
		       tree[i] = n;
		       n = mergeArbol(t1, t2);
		       tail.tree[i] = null;
		     break;
	    }
	}
	for(int k = 0; k < tail.tree.length; k++)
	    tail.tree[k] = null;
	tail.size = 0;
    }

    private Nodo<T> mergeArbol(Nodo<T> t1, Nodo<T> t2){
	if(t1.element.compareTo(t2.element) > 0)
	    return mergeArbol(t2, t1);
	t2.sonRight = t1.sonLeft;
	t1.sonLeft = t2;
	return t1;
    }

    /**
     * Inserta en la cola de prioridades, manteniendo el orden
     *
     */
    public void insert(T e){
	merge(new ColaBinomial<>(e));
    }

    /**
     * Encuentra el elemento mínimo
     */
    public T encuentraMinimo(){
	if(isEmpty())
	    throw new NoSuchElementException();
	return tree[minIndex()].element;
    }

    /**
     * Encuentra el índice del árbol que contiene el elemento mínimo
     */
  @SuppressWarnings("empty-statement")
    private int minIndex(){
	int i;
	int minInd;
	for(i = 0; tree[i] == null; i++)
	    ;
	for(minInd = i; i < tree.length; i++)
	    if(tree[i] != null &&
	       tree[i].element.compareTo(tree[minInd].element) < 0)
		minInd = i;
	return minInd;
    }


    /**
     *Elimina el elemento más pequeñoo de la cola de prioridad
     */
    public T removeMin(){
	if(isEmpty())
	    throw new NoSuchElementException();
	int minInd = minIndex();
	T eMin = tree[minInd].element;
	Nodo<T> remove = tree[minInd].sonLeft;
	ColaBinomial<T> removeC = new ColaBinomial<>();
	removeC.expandTree(minInd + 1);
	removeC.size = (1 << minInd) - 1;
	for(int j = minInd - 1; j >= 0; j--){
	    removeC.tree[j] = remove;
	    remove = remove.sonRight;
	    removeC.tree[j].sonRight = null;
	}
	tree[minInd] = null;
	size -= removeC.size + 1;
	merge(removeC);
	return eMin;
    }

   
    /**
     *Limpia la cola, dejándola vacia
     */
    public void clean(){
	size = 0;
	for(int i = 0; i < tree.length; i++)
	    tree[i] = null;
    }

    /**
     * Regresa la capacidad de la cola
     */
    private int capacity(){
	return (1 << tree.length) - 1;
    }
}
