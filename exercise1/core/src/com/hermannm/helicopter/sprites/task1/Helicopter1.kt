package com.hermannm.helicopter.sprites.task1

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Helicopter1(x: Float, y: Float) {
    companion object {
        const val SPEED = 100F
    }
    val sprite = Sprite(Texture("attackhelicopter.PNG"))
    val position: Vector3 = Vector3(x, y, 0F)
    private val velocity: Vector3 = Vector3(-SPEED, SPEED, 0F)
    val bounds: Rectangle
        get() = Rectangle(position.x, position.y, sprite.getWidth(), sprite.getHeight())
    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y, 0F)
        velocity.scl(1/deltaTime)
    }
    fun changeDirection(topCollision: Boolean, rightCollision: Boolean, bottomCollision: Boolean, leftCollision: Boolean) {
        if (topCollision) {
            velocity.y = -SPEED
        }
        if (rightCollision) {
            velocity.x = -SPEED
            sprite.setFlip(false, false)
        }
        if (bottomCollision) {
            velocity.y = SPEED
        }
        if (leftCollision) {
            velocity.x = SPEED
            sprite.setFlip(true, false)
        }
    }
}