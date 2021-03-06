[
    //handles permanents
    new LeavesBattlefieldTrigger(MagicTrigger.REPLACEMENT) {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final RemoveFromPlayAction act) {
            if (act.getPermanent() != permanent && act.to(MagicLocationType.Graveyard)) {
                act.setToLocation(MagicLocationType.Exile);
            }
            return MagicEvent.NONE;
        }
    },
    //handles cards
    new WouldBeMovedTrigger(MagicTrigger.REPLACEMENT) {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MoveCardAction act) {
            if (act.card != permanent.getCard() && act.to(MagicLocationType.Graveyard)) {
                act.setToLocation(MagicLocationType.Exile);
            }
            return MagicEvent.NONE;
        }
    }
]
