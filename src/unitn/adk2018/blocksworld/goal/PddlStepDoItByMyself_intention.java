package unitn.adk2018.blocksworld.goal;

import unitn.adk2018.Agent;
import unitn.adk2018.generic.goal.PddlStep_goal;
import unitn.adk2018.generic.goal.PddlStep_intention;

public class PddlStepDoItByMyself_intention extends PddlStep_intention {
	
	@Override
	public boolean context(Agent a, PddlStep_goal g) {
		return ( g.step.action.equals("pickup") || g.step.action.equals("putdown") || g.step.action.equals("stack") || g.step.action.equals("unstack") ) //supported actions
				&& g.step.args[0].equals( a.getName() ); // I personally have to do the action
	}
	
}
