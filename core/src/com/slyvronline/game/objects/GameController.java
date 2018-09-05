package com.slyvronline.game.objects;

import com.badlogic.gdx.controllers.Controller;

public class GameController {

	private String name;
	private float left;
	private float right;
	private float up;
	private float down;
	private float btnA;
	private float btnB;
	private float btnStart;
	private float btnSelect;
	private Controller control;
	
	public GameController(){
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLeft() {
		return left;
	}

	public void setLeft(float left) {
		this.left = left;
	}

	public float getRight() {
		return right;
	}

	public void setRight(float right) {
		this.right = right;
	}

	public float getUp() {
		return up;
	}

	public void setUp(float up) {
		this.up = up;
	}

	public float getDown() {
		return down;
	}

	public void setDown(float down) {
		this.down = down;
	}

	public float getBtnA() {
		return btnA;
	}

	public void setBtnA(float btnA) {
		this.btnA = btnA;
	}

	public float getBtnB() {
		return btnB;
	}

	public void setBtnB(float btnB) {
		this.btnB = btnB;
	}

	public float getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(float btnStart) {
		this.btnStart = btnStart;
	}

	public float getBtnSelect() {
		return btnSelect;
	}

	public void setBtnSelect(float btnSelect) {
		this.btnSelect = btnSelect;
	}

	public Controller getControl() {
		return control;
	}

	public void setControl(Controller control) {
		this.control = control;
	}
	
	
}
