package cardmaster.cards;

import cardmaster.DeckStack;
import cardmaster.Shape;

public class TripelCard extends Card {

	public TripelCard(Shape shape) {
		super(shape, "Tripel");
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
		score += (counter / 3) * 5;
		return score;
	}

}
