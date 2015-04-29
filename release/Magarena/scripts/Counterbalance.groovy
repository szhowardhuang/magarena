[
    new MagicWhenOtherSpellIsCastTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicCardOnStack cardOnStack) {
            return permanent.isEnemy(cardOnStack) ?
                new MagicEvent(
                    permanent,
                    permanent.getController(),
                    new MagicMayChoice("Reveal the top card of your library?"),
                    cardOnStack,
                    this,
                    "Reveal the top card of your library. " +
                    "If you do, counter RN if it has the same converted mana cost as the revealed card."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                final MagicCard card = event.getPlayer().getLibrary().getCardAtTop();
                game.doAction(new RevealAction(card));
                if (card.getConvertedCost() == event.getRefCardOnStack().getConvertedCost()) {
                    game.doAction(new CounterItemOnStackAction(event.getRefCardOnStack()));
                }
            }
        }
    }
]
