package com.project.game.status;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StateManager {
	
	private Stack<GameState> status;
	
	public StateManager() {
		status = new Stack<GameState>();
	}
	
	public void push(GameState state) {
		status.push(state);
	}
	
	public void pop() {
		status.pop();
	}
	
	public void set(GameState state) {
		status.pop();
		status.push(state);
	}
	
	public void update(float dt) {
		status.peek().update(dt);
	}
	
	public void render(SpriteBatch sb) {
		status.peek().render(sb);
	}
}
