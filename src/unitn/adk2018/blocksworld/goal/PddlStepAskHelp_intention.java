package unitn.adk2018.blocksworld.goal;

import unitn.adk2018.Agent;
import unitn.adk2018.Environment;
import unitn.adk2018.blocksworld.Blocksworld;
import unitn.adk2018.event.Goal;
import unitn.adk2018.event.Message;
import unitn.adk2018.generic.goal.PddlStep_goal;
import unitn.adk2018.generic.goal.ReachPddlGoal_goal;
import unitn.adk2018.generic.message.PddlAction_msg;
import unitn.adk2018.generic.message.Request_msg;
import unitn.adk2018.intention.Intention;
import unitn.adk2018.pddl.PddlClause;

public class PddlStepAskHelp_intention extends Intention<PddlStep_goal> {
	
	@Override
	public boolean context(Agent a, PddlStep_goal g) {
		return (g.step.action.equals("putdown") || g.step.action.equals("stack") ) //putdown or unstack
				&& !g.step.args[0].equals( a.getName() ); //its not me that has to do the action
	}
	
	private Message reqMsg;
	
	@Override
	public Next step0(IntentionInput in) {
		String nameOfAgentToAskForHelp = in.event.step.args[0];
		Goal g1 = new PddlStep_goal( event.step );
		reqMsg = new Request_msg( agent.getName(), nameOfAgentToAskForHelp, g1 );
		Environment.sendMessage ( reqMsg );
		return waitUntil( this::stepEnd, reqMsg.wasHandled() ); //wait until request has been succeeded then continue
	}
	
	public Next stepEnd(IntentionInput in) {
		if ( reqMsg.wasHandledWithSuccess().isTrue() )
			return null; //success
		else
			return waitFor(null, 0); //fail
	}
	
}
