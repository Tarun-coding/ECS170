
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.lang.Math;
/// A sample AI that takes a very suboptimal path.
/**
 * This is a sample AI that moves as far horizontally as necessary to reach the target,
 * then as far vertically as necessary to reach the target.  It is intended primarily as
 * a demonstration of the various pieces of the program.
 * 
 */


public class DijikstraTwo implements AIModule
{
	
	
	
	/// Creates the path to the goal.
    public List<Point> createPath(final TerrainMap map)
    {
        // Holds the resulting path
          ArrayList<Point> path = new ArrayList<Point>();

	HashMap<Point, Tuple> pointToState=new HashMap<Point, Tuple>();         
	 // Keep track of where we are and add the start point.
        final Point CurrentPoint = map.getStartPoint();
	path.add(new Point(CurrentPoint));
	
	
	Tuple startingState= new Tuple(CurrentPoint,0.0,path,getHeuristic(CurrentPoint,map));
	pointToState.put(CurrentPoint,startingState);
	PriorityQueue<Tuple> PQueue=new PriorityQueue<Tuple>();

	PQueue.add(startingState);
	
	
	HashSet<Point> explored = new HashSet<Point>();
	
	while(!PQueue.isEmpty()){
		
		Tuple returnedState=PQueue.poll();
		Point returnedPoint=returnedState.getPoint();
		double returnedCost=returnedState.getCost(); //this is g(n)

		pointToState.remove(returnedPoint);
		
		System.out.println(returnedCost);		
		
		
          ArrayList<Point> pathTwo = new ArrayList<Point>(returnedState.getParents());
		
		if(returnedPoint.equals(map.getEndPoint())){
			
			return pathTwo;
		}
		
		explored.add(new Point(returnedPoint));
		
		Point[] actions=map.getNeighbors(returnedPoint);

		for(Point currentAction: actions){
			
			
          		ArrayList<Point> pathThree = new ArrayList<Point>(returnedState.getParents());
			
	
			double totalPathCost=returnedCost+map.getCost(returnedPoint,currentAction);
			double FofN=totalPathCost+getHeuristic(currentAction,map);
			if(!((explored.contains(currentAction))||(pointToState.containsKey(currentAction)))){ 
				
				pathThree.add(new Point(currentAction));
				
				Tuple addToQueue=new Tuple(currentAction,totalPathCost,pathThree,FofN);
				PQueue.add(addToQueue);

				
				pointToState.put(currentAction,addToQueue);
				
			}
			
			else if(pointToState.containsKey(currentAction)){
				if(pointToState.get(currentAction).getCost()>totalPathCost){
					
					PQueue.remove(pointToState.get(currentAction));
					pointToState.remove(currentAction);

					pathThree.add(new Point(currentAction));
					Tuple addToQueue=new Tuple(currentAction,totalPathCost,pathThree,FofN);
					PQueue.add(addToQueue);
					pointToState.put(currentAction,addToQueue);


				}
			}
			
				
			

		}
		
	}

	
      
      
        
	// We're done!  Hand it back.
        return path;
    }

    public double getHeuristic(Point p,final TerrainMap map){

 	    
	//Division heuristic
//	return 0.5 * Math.max(Math.abs(p.x - map.getEndPoint().x),Math.abs(p.y - map.getEndPoint().y));

	    double differenceInHeight=map.getTile(map.getEndPoint())-map.getTile(p);
	     
   	 return differenceInHeight+Math.max(Math.abs(p.x-map.getEndPoint().x),Math.abs(p.y-map.getEndPoint().y));
    //return 0;
    
    }
   



public class Tuple implements Comparable<Tuple>{
	private Point x;
	private double y; //This is g(n)
	private ArrayList<Point> parents;
	private double z; //This is f(n)

	public Point getPoint() {return x;}
	public double getCost() {return y;}
	public ArrayList<Point> getParents() {return parents;} 
	public double getFofN() {return z;}

	public Tuple(Point x,double y,ArrayList<Point> parents,double z){
		this.x= x;
		this.y= y;
		this.parents = parents;
		this.z = z;
		
	}
	public int compareTo(Tuple other){
		if(getFofN()==other.getFofN()){
		//if(getCost()==other.getCost()){
			return 0;
		}else if(getFofN()>other.getFofN()){
		//}else if(getCost()>other.getCost()){
			return 1;
		}else{
			return -1;
		}
	}
}

}

