package cardmaster.cards;

import cardmaster.DeckStack;
import cardmaster.Shape;

public class PaarCard extends Card {

	public PaarCard(Shape shape) {
		super(shape, "Paar");
	}

	@Override
	public double calculatePoints(DeckStack d) {
		double score = 0;
		int counter = 0;
		for (int i = 0; i < d.getDrawPiles().length; i++) {
			if (d.getDrawPiles()[i].getCards().length != 0) {
				if (d.getDrawPiles()[i].seeTopCard().getShape() == this.getShape()) {
					counter++;
				}
			}
		}
		score += (counter / 2) * 2;
		return score;
	}

}
