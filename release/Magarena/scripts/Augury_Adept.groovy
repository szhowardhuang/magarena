[
    new MagicWhenSelfCombatDamagePlayerTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicDamage damage) {
            return new MagicEvent(
                permanent,
                this,
                "Reveal the top card of PN's library and put that card into PN's hand. PN gains life equal to its converted mana cost."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPlayer player = event.getPlayer();
            final MagicCard card = player.getLibrary().getCardAtTop();
            game.doAction(new RevealAction(card));
            game.doAction(new RemoveCardAction(card, MagicLocationType.OwnersLibrary));
            game.doAction(new MoveCardAction(card, MagicLocationType.OwnersLibrary, MagicLocationType.OwnersHand));
            game.doAction(new ChangeLifeAction(player, card.getConvertedCost()));
        }
    }
]
