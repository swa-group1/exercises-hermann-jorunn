package com.hermannm.helicopter.sprites.task3

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Helicopter3(x: Float, y: Float) {
    companion object {
        const val SPEED = 100F
    }
    val position: Vector3 = Vector3(x, y, 0F)
    private val velocity: Vector3 = Vector3(-SPEED, SPEED, 0F)
    private val helicopterTextures: Array<TextureRegion> = arrayOf(
        TextureRegion(Texture("heli1.png")),
        TextureRegion(Texture("heli2.png")),
        TextureRegion(Texture("heli3.png")),
        TextureRegion(Texture("heli4.png"))
    )
    val bounds: Rectangle
        get() = Rectangle(position.x, position.y, getTexture().getTexture().getWidth().toFloat(), getTexture().getTexture().getHeight().toFloat())
    private val helicopterAnimation: Animation = Animation(
            helicopterTextures,
            0.5F
    )
    fun update(deltaTime: Float) {
        helicopterAnimation.update(deltaTime)
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y, 0F)
        velocity.scl(1 / deltaTime)
    }

    fun changeDirection(collisions: HashMap<String, Boolean>) {
        if (collisions["top"]!!) {
            velocity.y = -SPEED
        }
        if (collisions["right"]!!) {
            velocity.x = -SPEED
            if (getTexture().isFlipX) {
                getTexture().flip(false, false)
            }
        }
        if (collisions["bottom"]!!) {
            velocity.y = SPEED
        }
        if (collisions["left"]!!) {
            velocity.x = SPEED
            if (!getTexture().isFlipX) {
                getTexture().flip(true, false)
            }
        }
    }
    fun getTexture(): TextureRegion {
        return helicopterAnimation.getFrame()
    }
}