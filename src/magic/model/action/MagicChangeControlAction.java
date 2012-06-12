package magic.model.action;

import magic.model.MagicGame;
import magic.model.MagicPermanent;
import magic.model.MagicPermanentState;
import magic.model.MagicPlayer;
import magic.model.trigger.MagicTriggerType;
import magic.model.mstatic.MagicStatic;
import magic.model.mstatic.MagicLayer;

public class MagicChangeControlAction extends MagicAction {

    private final MagicPlayer curr;
    private final MagicPermanent perm;
    private final int score;
    
    public MagicChangeControlAction(
            final MagicPlayer curr, 
            final MagicPermanent perm, 
            final int score) {
        this.curr = curr;
        this.perm = perm;
        this.score = score;
    }
    
    @Override
    public void doAction(final MagicGame game) {
        final MagicPlayer prev = curr.getOpponent();

        // Execute trigger here so that full permanent state is preserved.
        game.executeTrigger(MagicTriggerType.WhenLoseControl, perm);
        
        prev.removePermanent(perm);
        curr.addPermanent(perm);

        perm.setState(MagicPermanentState.Summoned);
        
        if (perm.getPairedCreature().isValid()) {;
            game.doAction(new MagicSoulbondAction(perm,perm.getPairedCreature(),false));
        }

        setScore(curr, score + perm.getScore());
        game.setStateCheckRequired();
    }

    @Override
    public void undoAction(final MagicGame game) {
        final MagicPlayer prev = curr.getOpponent();
        curr.removePermanent(perm);
        prev.addPermanent(perm);
        perm.clearState(MagicPermanentState.Summoned);
        game.setStateCheckRequired();
    }
}
