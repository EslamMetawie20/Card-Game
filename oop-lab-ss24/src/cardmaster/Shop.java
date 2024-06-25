package cardmaster;

import cardmaster.cards.Card;
import cardmaster.upgrades.DeckStackUpgrade;
import cardmaster.upgrades.HandUpgrade;
import cardmaster.upgrades.ShopUpgrade;
import cardmaster.upgrades.Upgrade;

public class Shop {

	private Item[] item; // Array to hold cards available in the shop
	private int[] shopPrice;
	private int arrayLength;
	private double ownedCredits;
	private boolean boughtCard;
	CardFactory factory;
	Upgrade[] upgrade;


	/**
	 * Constructor for Shop class
	 * 
	 * @param ownedCredits The amount of credits the player owns
	 */
	public Shop(CardFactory f) {
		Upgrade[] upgradetemp = { new DeckStackUpgrade(0), new HandUpgrade(0), new ShopUpgrade(0) };
		upgrade=upgradetemp;
		this.factory = f;
		this.arrayLength = 8;
		this.ownedCredits = 10;
		boughtCard = false;
		item = new Item[arrayLength];
		shopPrice = new int[arrayLength];
		this.generateCards();
	}

	/**
	 * Check if the shop is empty
	 * 
	 * @return true if the shop is empty, false otherwise
	 */
	public boolean isShopEmpty() {
		if (item.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Get the number of items in the shop
	 * 
	 * @return The number of items in the shop
	 */
	public int getShopItemCount() {
		return item.length;
	}

	/**
	 * Get description of items in the shop
	 * 
	 * @return A string containing the description of items in the shop
	 */
	public String getShopItemDescription() {

		String description = "";
		for (int i = 0; i < item.length; i++) {
			description += i + 1 + ". " + item[i].toString(); // Display card information
			if (i < item.length - 1) {
				description += ", ";
			}
		}
		return description;
	}

	/**
	 * Get the price of an item in the shop
	 * 
	 * @param shopItemIndex Index of the item in the shop
	 * @return The price of the item at the specified index
	 */
	public int getShopItemPrice(int shopItemIndex) {
		return shopPrice[shopItemIndex];
	}

	/**
	 * Buy an item from the shop
	 * 
	 * @param shopItemIndex Index of the item to buy
	 * @return true if the item is successfully bought, false otherwise
	 */
	public int removePrice(int shopItemIndex) {
		if (isShopEmpty()) {
			return -1;
		}
		int tempPrice = shopPrice[shopItemIndex];

		// Copying Cards Array but without bought card
		int[] temp = new int[shopPrice.length - 1];
		int k = 0;
		for (int i = 0; i < temp.length; i++) {
			if (shopItemIndex != k) {
				temp[i] = shopPrice[k];
			} else {
				i--;
			}
			k++;
		}
		shopPrice = temp;

		boughtCard = true;
		return tempPrice; // Purchase successful
	}

	/**
	 * Removes the price with
	 * 
	 * @param shopItemIndex Index of the item to buy
	 * @return true if the item is successfully bought, false otherwise
	 */
	public Item removeItem(int shopItemIndex) {
		if (isShopEmpty()) {
			return null;
		}

		// check too expensive
		int priceOfCard = getShopItemPrice(shopItemIndex);
		if (priceOfCard > ownedCredits) {
			return null;
		}

		Item tempCard = this.getCardsAtIndex(shopItemIndex);

		ownedCredits -= priceOfCard; // Deduct the price from the account balance

		// Copying Cards Array but without bought card
		Item[] temp = new Item[item.length - 1];
		int k = 0;
		for (int i = 0; i < temp.length; i++) {
			if (shopItemIndex != k) {
				temp[i] = item[k];
			} else {
				i--;
			}
			k++;
		}
		item = temp;

		boughtCard = true;
		this.removePrice(shopItemIndex);
		return tempCard; // Purchase successful
	}

	public Item remove(Item item) {
		// Finding Index to remove card
		int index = -1;
		for (int i = 0; i < this.getCards().length; i++) {
			if (item == this.getCards()[i]) {
				index = i;
				break;
			}
		}
		assert index > -1;
		return removeItem(index);
	}

	/**
	 * End shopping session and clear the shop
	 */
	public void endShopping() {
		this.generateCards();
	}

	/**
	 * Generate cards available in the shop
	 * 
	 */
	public void generateCards() {
		item=new Item[this.arrayLength];
		shopPrice=new int[this.arrayLength];
		for (int i = 0; i < item.length - this.upgrade.length; i++) {
			item[i] = this.factory.createRandom();// Create cards with factory
			shopPrice[i] = item[i].generatePrice(this.ownedCredits);
		}

		int k = 0;
		for (int i = item.length - this.upgrade.length; i < item.length; i++) {
			item[i] = this.upgrade[k++];
			shopPrice[i] = item[i].generatePrice(this.ownedCredits);
		}

	}

	/**
	 * Get all cards available in the shop
	 * 
	 * @return Array containing all cards in the shop
	 */
	public Item[] getCards() {
		return item;
	}

	public Item getCardsAtIndex(int index) {
		return item[index];
	}

	public double getCredits() {
		return ownedCredits;
	}

	public void setCards(Card[] cards) {
		this.item = cards;
	}

	public void setCredits(double credits) {
		this.ownedCredits = credits;
	}

	public boolean isBoughtCard() {
		return boughtCard;
	}

	public int getArrayLength() {
		return arrayLength;
	}

	public void setArrayLength(int arrayLength) {
		this.arrayLength = arrayLength;
	}

	public int[] getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(int[] shopPrice) {
		this.shopPrice = shopPrice;
	}

	public void setShopItemPriceAtIndex(int index, int price) {
		shopPrice[index] = price;
	}

	
	public Upgrade[] getUpgrade() {
		return upgrade;
	}

}
