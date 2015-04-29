[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Draw),
        "Card"
    ) {
        @Override
        public Iterable<MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostEvent(source,"{2}")
            ];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "Put the top card of PN's graveyard on the bottom of PN's library."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPlayer player = event.getPlayer();
            final MagicCard card = player.getGraveyard().getCardAtTop();
            game.doAction(new RemoveCardAction(card, MagicLocationType.Graveyard));
            game.doAction(new MoveCardAction(card, MagicLocationType.Graveyard, MagicLocationType.BottomOfOwnersLibrary));
            game.logAppendMessage(player," ("+card.getName()+")");
        }
    }
]
