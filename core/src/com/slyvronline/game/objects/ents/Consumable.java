package com.slyvronline.game.objects.ents;

import java.util.ArrayList;

import com.slyvronline.game.objects.Ent;

public class Consumable extends Ent{

	private int size;
	private int animateCounter;
	private int animateCounterMax;
	private boolean animateCountUp;
	private float animateAmount;
	private int animateSpeed;
	private boolean consumed;
	private int consumeCount;
	private int consumeCountMax = 50;
	private boolean destroy;
	private boolean shakeRight;
	private boolean shakeLeft;
	private ArrayList<String> animates;
	
	public Consumable(){
		
	}
	
	public Consumable(Consumable c){
		super(c);
		this.size = c.getSize();
		this.animateCounter = c.getAnimateCounter();
		this.animateCounterMax = c.getAnimateCounterMax();
		this.animateCountUp = c.isAnimateCountUp();
		this.animateAmount = c.getAnimateAmount();
		this.animateSpeed = c.getAnimateSpeed();
		this.consumed = false;
		this.animates = new ArrayList<String>();
		animates.addAll(c.getAnimates());
	}
	
	public void animate(){
		consume();
		for(String animate : animates){
			if (animate.equals("consume")){
				consume();
			}
			else if (animate.equals("hop")){
				hop();
			}
			else if (animate.equals("shimmy")){
				shimmy();
			}
		}
	}
	
	public void consume(){
		if (consumed){
			if (this.getConsumeCount() < this.consumeCountMax){
				this.setConsumeCount(this.getConsumeCount() + 1);
				this.setRotation(this.getRotation() + 10.0f);
				this.setWidth(this.getWidth() - 1.0f);
				this.setHeight(this.getHeight() - 1.0f);
				this.setCenterX((int) (this.getWidth()/2));
				this.setCenterY((int) (this.getHeight()/2));
			}
			else if (this.getConsumeCount() >= this.consumeCountMax){
				this.setDestroy(true);
			}
		}
	}
	
	public void hop(){
		if (this.getAnimateCounter() < this.getAnimateCounterMax() && this.isAnimateCountUp()){
			this.setAnimateCounter(this.getAnimateCounter() + this.getAnimateSpeed());
			this.setY(this.getY() + this.getAnimateAmount());
		}
		else if (this.getAnimateCounter() == this.getAnimateCounterMax() && this.isAnimateCountUp()){
			this.setAnimateCountUp(false);
		}
		else if (this.getAnimateCounter() > 0 && !this.isAnimateCountUp()){
			this.setAnimateCounter(this.getAnimateCounter() - this.getAnimateSpeed());
			this.setY(this.getY() - this.getAnimateAmount());
		}
		else if (this.getAnimateCounter() == 0 && !this.isAnimateCountUp()){
			this.setAnimateCountUp(true);
		}
	}
	
	public void shimmy(){
		if (this.getAnimateCounter() < this.getAnimateCounterMax() && this.isAnimateCountUp()){
			this.setAnimateCounter(this.getAnimateCounter() + this.getAnimateSpeed());
			this.setX(this.getX() + this.getAnimateAmount());
		}
		else if (this.getAnimateCounter() == this.getAnimateCounterMax() && this.isAnimateCountUp()){
			this.setAnimateCountUp(false);
		}
		else if (this.getAnimateCounter() > 0 && !this.isAnimateCountUp()){
			this.setAnimateCounter(this.getAnimateCounter() - this.getAnimateSpeed());
			this.setX(this.getX() - this.getAnimateAmount());
		}
		else if (this.getAnimateCounter() == 0 && !this.isAnimateCountUp()){
			this.setAnimateCountUp(true);
		}
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getAnimateCounter() {
		return animateCounter;
	}

	public void setAnimateCounter(int animateCounter) {
		this.animateCounter = animateCounter;
	}

	public int getAnimateCounterMax() {
		return animateCounterMax;
	}

	public void setAnimateCounterMax(int animateCounterMax) {
		this.animateCounterMax = animateCounterMax;
	}

	public boolean isAnimateCountUp() {
		return animateCountUp;
	}

	public void setAnimateCountUp(boolean animateCountUp) {
		this.animateCountUp = animateCountUp;
	}

	public float getAnimateAmount() {
		return animateAmount;
	}

	public void setAnimateAmount(float animateAmount) {
		this.animateAmount = animateAmount;
	}

	public int getAnimateSpeed() {
		return animateSpeed;
	}

	public void setAnimateSpeed(int animateSpeed) {
		this.animateSpeed = animateSpeed;
	}

	public boolean isConsumed() {
		return consumed;
	}

	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}

	public int getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(int consumeCount) {
		this.consumeCount = consumeCount;
	}

	public int getConsumeCountMax() {
		return consumeCountMax;
	}

	public void setConsumeCountMax(int consumeCountMax) {
		this.consumeCountMax = consumeCountMax;
	}

	public boolean isDestroy() {
		return destroy;
	}

	public void setDestroy(boolean destroy) {
		this.destroy = destroy;
	}

	public boolean isShakeRight() {
		return shakeRight;
	}

	public void setShakeRight(boolean shakeRight) {
		this.shakeRight = shakeRight;
	}

	public boolean isShakeLeft() {
		return shakeLeft;
	}

	public void setShakeLeft(boolean shakeLeft) {
		this.shakeLeft = shakeLeft;
	}

	public ArrayList<String> getAnimates() {
		return animates;
	}

	public void setAnimates(ArrayList<String> animates) {
		this.animates = animates;
	}
	
}
