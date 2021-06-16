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
public class MiniMax extends AIModule
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
	/*	
	
		whichPlayer=game.getActivePlayer();
		final Random r = new Random();
		// set chosenMove to a random column
		int move = r.nextInt(game.getWidth());
		while(!game.canMakeMove(move))
			move = r.nextInt(game.getWidth());
		chosenMove = move;
		//System.out.println(game.getAt(10,20));
		

		int horizontalGood=0;
		int horizontalBad=0;
			for(int i=0;i<game.getHeight();i++){
				for(int j=0;j<game.getWidth()-2;j++){
					if((game.getAt(j,i)==whichPlayer) && (game.getAt(j+1,i)==whichPlayer) && (game.getAt(j+2,i)==whichPlayer)){
						horizontalGood++;
					}else if(game.getAt(j,i)!=0 && game.getAt(j+1,i)!=0 && game.getAt(j+2,i)!=0){
						horizontalBad++;
					}
				}
			}


		int verticalGood=0;
		int verticalBad=0;
			for(int i=0;i<game.getHeight()-2;i++){
				for(int j=0;j<game.getWidth();j++){
					if((game.getAt(j,i)==whichPlayer) && (game.getAt(j,i+1)==whichPlayer) && (game.getAt(j,i+2)==whichPlayer)){
						verticalGood++;
					}else if(game.getAt(j,i)!=0 && game.getAt(j,i+1)!=0 && game.getAt(j,i+2)!=0){
						verticalBad++;
					}
				}
			}

		
			System.out.println("verticalGood: "+verticalGood);
			System.out.println("verticalBad: "+verticalBad);	
*/
			
		/*
		HashMap<Integer,Integer> gfg= new HashMap<Integer,Integer>();
		
		//gfg.put(move,evaluationTable[game.getHeightAt(move)][move]);

		gfg.put(1,5);
		gfg.put(2,2);
		gfg.put(3,3);
		gfg.put(4,6);
		gfg=sortHashMapByValues(gfg);
		//System.out.println(gfg);
		//ArrayList<Map.Entry<Integer,Integer>> arr=new ArrayList<Map.Entry<Integer,Integer>>();
		//System.out.println(gfg);

		for(Map.Entry<Integer,Integer>entry:gfg.entrySet()){
			System.out.println("key ="+entry.getKey()+"value ="+entry.getValue());
			//arr.add(entry);
		}
		*/
		//System.out.println("done");
		
	
			

	/*	
		game.makeMove(move);
		Tuple startingState= new Tuple(move,game,1);
		gfg.add(startingState);
		//System.out.println(startingState.getMove());
	*/
		
		
//		if((startingState.getBoard()).isGameOver()){
//			int k=(startingState.getBoard()).getWinner();
//			System.out.println(k);
//		}		
	

		
		/*
		GameStateModule copy=game.copy();
		copy.makeMove(move);
		if(copy.isGameOver()){
			System.out.println("game is over");
		}
		*/
		
		
//		int v=Max_Value(game,1);
		

		
		
		whichPlayer=game.getActivePlayer();
		 int v=Max_Value(game,1,-10000,10000);
	//	System.out.println("before chosenMove: "+v);	
		if(!game.canMakeMove(v)){
			System.out.println("can't make move");
		}
		chosenMove = v;
		
		
	}
	
	public int Max_Value(final GameStateModule game,int level,int alpha,int beta){
		
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
/*	
		if(level==11){
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
*/			
		//horizontal check
			/*
			for(int i=0;i<game.getHeight();i++){
				for(int j=0;j<game.getWidth()-2;j++){
					if((game.getAt(j,i)==whichPlayer) && (game.getAt(j+1,i)==whichPlayer) && (game.getAt(j+2,i)==whichPlayer)){
						numberOfThrees++;
					}else if(game.getAt(j,i)!=0 && game.getAt(j+1,i)!=0 && game.getAt(j+2,i)!=0){
						numberOfThrees--;
					}
				}
			}


		//vertical check
			for(int i=0;i<game.getHeight()-2;i++){
				for(int j=0;j<game.getWidth();j++){
					if((game.getAt(j,i)==whichPlayer) && (game.getAt(j,i+1)==whichPlayer) && (game.getAt(j,i+2)==whichPlayer)){
						numberOfThrees++;
					}else if(game.getAt(j,i)!=0 && game.getAt(j,i+1)!=0 && game.getAt(j,i+2)!=0){
						numberOfThrees--;
					}
				}
			}
			*/
		//	return utility+(numberOfThrees*0);
				
//		}
			
		HashMap<Integer,Integer> indexToValues=new HashMap<Integer,Integer>();
		//ArrayList<Integer> values=new ArrayList<Integer>();
		for(int i=0;i<game.getWidth();i++){
			if(game.canMakeMove(i)){
				indexToValues.put(i,(evaluationTable[game.getHeightAt(i)][i]));
				//values.add(evaluationTable[game.getHeightAt(i)][i]);
			}
		}
		//Collections.sort(values,Collections.reverseOrder());
			
		indexToValues=sortHashMapByValues(indexToValues);
		int v=-10000;
	//for(int i=0;i<game.getWidth();i++){
		for(Map.Entry<Integer,Integer> entry: indexToValues.entrySet()){
		
		//	if(game.canMakeMove(i)){
		            	GameStateModule copy=game.copy();
		//		copy.makeMove(i);
				copy.makeMove(entry.getKey());
		//		Tuple state=new Tuple(i,copy);//,unknown value);
			        Tuple state=new Tuple(entry.getKey(),copy);
	//			indexToValues.remove(entry.getKey());
				state.setValue(Min_Value(state.getBoard(),level+1,alpha,beta));
				
				sucessorStates.add(state);

				v=Math.max(v,state.getValue());
				if(v>=beta){
					if(level!=1){
						return v;
					}else{
					//	System.out.println("we are here");
						return state.getMove();
					}
				}
				alpha=Math.max(alpha,v);
		//	}
		}
		
			
		
		if(level==1){
		//	for(Tuple state:sucessorStates){
		//		System.out.println(state.getMove()+","+state.getValue());
		//	}
		//	System.out.println(sucessorStates.size());
		/*
			if(sucessorStates.size()==1){
				System.out.println("size is one");
				System.out.println(sucessorStates.get(0).getMove());
			}
			*/
			for(Tuple state:sucessorStates){
				//System.out.println(state.getValue());
				
				if(state.getValue()==v){
			//		System.out.println(state.getValue());
			//		System.out.println(state.getMove());
			//		System.out.println("done");
				/*
					if(sucessorStates.size()==1){
						System.out.println("returned move");
						System.out.println(state.getMove());
					}
					*/
					return state.getMove();
				}
			}	
		}
		return v;

	
	}


	public int Min_Value(final GameStateModule game,int level,int alpha,int beta){
		
		//ArrayList<Tuple> sucessorStates = new ArrayList<Tuple>();

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
		int v=10000;
/*		
		for(int i=0;i<game.getWidth();i++){
			if(game.canMakeMove(i)){
		            	GameStateModule copy=game.copy();
				copy.makeMove(i);
				Tuple state=new Tuple(i,copy);//,unknown value);
			        state.setValue(Max_Value(state.getBoard(),level+1,alpha,beta));
				
				sucessorStates.add(state);
				v=Math.min(v,state.getValue());
				if(v<=alpha){
					return v;
				}
				beta=Math.min(beta,v);
			}
		}
		
*/
			
		HashMap<Integer,Integer> indexToValues=new HashMap<Integer,Integer>();
		//ArrayList<Integer> values=new ArrayList<Integer>();
		for(int i=0;i<game.getWidth();i++){
			if(game.canMakeMove(i)){
				indexToValues.put(i,(evaluationTable[game.getHeightAt(i)][i]));
				//values.add(evaluationTable[game.getHeightAt(i)][i]);
			}
		}
		//Collections.sort(values,Collections.reverseOrder());
			
			indexToValues=sortHashMapByValues(indexToValues);
		for(Map.Entry<Integer,Integer> entry: indexToValues.entrySet()){
		
		//	if(game.canMakeMove(i)){
		            	GameStateModule copy=game.copy();
		//		copy.makeMove(i);
				copy.makeMove(entry.getKey());
		//		Tuple state=new Tuple(i,copy);//,unknown value);
			        Tuple state=new Tuple(entry.getKey(),copy);
	//			indexToValues.remove(entry.getKey());
				state.setValue(Max_Value(state.getBoard(),level+1,alpha,beta));
				
		//		sucessorStates.add(state);

				v=Math.min(v,state.getValue());
				if(v<=alpha){
					return v;
				}
				beta=Math.min(beta,v);
		//	}
		}
		
		return v;
	}

	
public LinkedHashMap<Integer, Integer> sortHashMapByValues(
        HashMap<Integer, Integer> passedMap) {
    List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
    List<Integer> mapValues = new ArrayList<>(passedMap.values());
    Collections.sort(mapValues,Collections.reverseOrder());
    Collections.sort(mapKeys,Collections.reverseOrder());

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

