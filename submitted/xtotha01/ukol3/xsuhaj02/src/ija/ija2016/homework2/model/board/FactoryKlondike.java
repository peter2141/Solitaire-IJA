package ija.ija2016.homework2.model.board;

import ija.ija2016.homework2.model.cards.*;

public class FactoryKlondike extends AbstractFactorySolitaire {

	@Override
	public CardDeck createCardDeck() {
		xCardDeck array = new xCardDeck();
		for(int i=1; i<=13; i++) {
			array.put(createCard(Card.Color.HEARTS, i));
		}
		for(int i=1; i<=13; i++) {
			array.put(createCard(Card.Color.SPADES, i));
		}
		for(int i=1; i<=13; i++) {
			array.put(createCard(Card.Color.DIAMONDS, i));
		}
		for(int i=1; i<=13; i++) {
			array.put(createCard(Card.Color.CLUBS, i));
		}
		return array;
	}

	@Override
	public Card createCard(Card.Color color, int value) {
		if (!(color == Card.Color.HEARTS || color == Card.Color.SPADES || color == Card.Color.DIAMONDS || color == Card.Color.CLUBS)) return null;
		if (value<1 || 13<value) return null;
		else return new xCard(color, value);
	}

	@Override
	public CardDeck createTargetPack(Card.Color color) {
		if (!(color == Card.Color.HEARTS || color == Card.Color.SPADES || color == Card.Color.DIAMONDS || color == Card.Color.CLUBS)) return null;
		return new xCardStackColor(color);
	}

	@Override
	public CardStack createWorkingPack() {
		return new xCardStack();
	}


}