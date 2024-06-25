package cardmaster.cards;

import cardmaster.DeckStack;
import cardmaster.Shape;

public class ChanceCard extends Card {

	public ChanceCard(Shape shape) {
		super(shape, "Chance");
	}

	@Override
	public double calculatePoints(DeckStack d) {
		double score = 0;

		// Booleans to check for presence of each shape
		boolean checkCircle = false;
		boolean checkSquare = false;
		boolean checkStar = false;

		// Calculate score by the forms
		for (int i = 0; i < d.getDrawPiles().length; i++) {

			if (d.getDrawPiles()[i].getCards().length == 0) {
				continue;
			}

			// not empty piles
			score += 0.5;

			// Points for every unique shape
			Shape shape = d.getDrawPiles()[i].seeTopCard().getShape();
			if (shape == Shape.CIRCLE) {
				checkCircle = true;
			}
			if (shape == Shape.SQUARE) {
				checkSquare = true;
			}
			if (shape == Shape.STAR) {
				checkStar = true;
			}
		}

		// Points for boolean checks
		if (checkCircle) {
			score += 0.5;
		}
		if (checkSquare) {
			score += 0.5;
		}
		if (checkStar) {
			score += 0.5;
		}

		return score;
	}
}
