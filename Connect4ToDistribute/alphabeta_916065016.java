// (c) Ian Davidson, Leo Shamis U.C. Davis 2019

import java.util.*;

import java.lang.Math;
/// Sample AI module that picks random moves at each point.
/**
 * This AI performs random actions.  It is meant to illustrate the basics of how to
 * use the AIModule and GameStateModule classes.
 *
 * Since this terminates in under a millisecond, there is no reason to check for
 * the terminate flag.  However, your AI needs to check for the terminate flag.
 *
 * @author Leonid Shamis
 */
public class alphabeta_916065016 extends AIModule
{
	//ArrayList<Tuple> gfg = new ArrayList<Tuple>();
	int whichPlayer;
	int[][] evaluationTable={{3,4,5,7,5,3,4},
				 {4,6,8,10,8,6,4},
			 	 {5,8,11,13,11,8,5},
				 {5,8,11,13,11,8,5},
				 {4,6,8,10,8,6,4},
				 {3,4,5,7,5,4,3}};	 
	public void getNextMove(final GameStateModule game)
	{
		
		whichPlayer=game.getActivePlayer();
		 int v=Max_Value(game,1,-10000,10000,3);
		chosenMove = v;
		
		
	}
	
	public int Max_Value(final GameStateModule game,int level,int alpha,int beta,int recentMove){
		
		ArrayList<Tuple> sucessorStates = new ArrayList<Tuple>();

		if(game.isGameOver()){
	
			//return the state value
			int winner;
			winner=game.getWinner();
			if(winner==whichPlayer){
				return 10000;        //if game is won
			}else if(winner==0){
				return 0;	//if game is drawn
			}else{
				return -10000;	//if game is lost
			}
		
		}
			
		HashMap<Integer,Integer> indexToValues=new HashMap<Integer,Integer>();
		for(int i=0;i<game.getWidth();i++){
			if(game.canMakeMove(i)){
				indexToValues.put(i,Math.abs(i-recentMove) );//(evaluationTable[game.getHeightAt(i)][i]));
			}
		}
			
		indexToValues=successorFunction(indexToValues);
		int v=-10000;
		for(Map.Entry<Integer,Integer> entry: indexToValues.entrySet()){
		
		            	GameStateModule copy=game.copy();
				copy.makeMove(entry.getKey());
			        Tuple state=new Tuple(entry.getKey(),copy);
				state.setValue(Min_Value(state.getBoard(),level+1,alpha,beta,entry.getKey()));
				
				sucessorStates.add(state);

				v=Math.max(v,state.getValue());
			
				if(v>=beta){
					if(level!=1){
						return v;
					}else{
						return state.getMove();
					}
				}
				alpha=Math.max(alpha,v);
			
		}
		
			
		
		if(level==1){
			for(Tuple state:sucessorStates){
				
				
				if(state.getValue()==v){
					return state.getMove();
				}
			}	
		}
		return v;

	
	}


	public int Min_Value(final GameStateModule game,int level,int alpha,int beta,int recentMove){
		

		if(game.isGameOver()){
	
			//return the state value
			int winner;
			winner=game.getWinner();
			if(winner==whichPlayer){
				return 10000;        //if game is won
			}else if(winner==0){
				return 0;	//if game is drawn
			}else{
				return -10000;	//if game is lost
			}

		}

		if(level==10){
			return evaluationFunction(game);
		}
		int v=10000;
			
		HashMap<Integer,Integer> indexToValues=new HashMap<Integer,Integer>();
		for(int i=0;i<game.getWidth();i++){
			if(game.canMakeMove(i)){
				indexToValues.put(i,Math.abs(i-recentMove));//(evaluationTable[game.getHeightAt(i)][i]));
			}
		}
			
			indexToValues=successorFunction(indexToValues);
		for(Map.Entry<Integer,Integer> entry: indexToValues.entrySet()){
		
		            	GameStateModule copy=game.copy();
				copy.makeMove(entry.getKey());
			        Tuple state=new Tuple(entry.getKey(),copy);
				state.setValue(Max_Value(state.getBoard(),level+1,alpha,beta,entry.getKey()));
				

				v=Math.min(v,state.getValue());
			
				if(v<=alpha){
					return v;
				}
				beta=Math.min(beta,v);
		
		}
		
		return v;
	}

public int evaluationFunction(final GameStateModule game){
	
		int utility = 0;
		int sum=0;
		int numberOfThrees=0;
		for(int i=0;i<game.getHeight();i++){
			for(int j=0;j<game.getWidth();j++){
				if(game.getAt(j,i)==whichPlayer){
					sum+=evaluationTable[i][j];
				}else if(game.getAt(j,i)!=0){
					sum -= evaluationTable[i][j];
				}
			}
		}
		 	utility=sum;
			return utility;
}
	
public LinkedHashMap<Integer, Integer> successorFunction(
        HashMap<Integer, Integer> passedMap) {
    List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
    List<Integer> mapValues = new ArrayList<>(passedMap.values());
    Collections.sort(mapValues);//,Collections.reverseOrder());
    Collections.sort(mapKeys);//,Collections.reverseOrder());

    LinkedHashMap<Integer, Integer> sortedMap =
        new LinkedHashMap<>();

    Iterator<Integer> valueIt = mapValues.iterator();
    while (valueIt.hasNext()) {
        Integer val = valueIt.next();
        Iterator<Integer> keyIt = mapKeys.iterator();

        while (keyIt.hasNext()) {
            Integer key = keyIt.next();
            Integer comp1 = passedMap.get(key);
            Integer comp2 = val;

            if (comp1.equals(comp2)) {
                keyIt.remove();
                sortedMap.put(key, val);
                break;
            }
        }
    }
    return sortedMap;
}



public class Tuple{
	private int x;//move to get to the current node
	private GameStateModule y;//the board at the current node
	private int z; //the value of the node

	public int getMove() {return x;}
	public GameStateModule getBoard() {return y;}
	public int getValue() {return z;}
	
	public Tuple(int x,GameStateModule y){
		this.x= x;
		this.y= y;
		//this.z = z;
		
	}
	public void setValue(int z){
		this.z=z;
	}
}


}

