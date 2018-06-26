package com.example.maith.sharinginternetaccess.animation.sprite

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.maith.sharinginternetaccess.R
import kotlinx.android.synthetic.main.fragment_animation.*

class AnimationFragment : Fragment(), Animation.AnimationListener {

    var isAnim = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgAnim.setBackgroundResource(R.drawable.animation_run)
        val frameAnimation = imgAnim.background as AnimationDrawable
        frameAnimation.start()
        val animUpRight = AnimationUtils.loadAnimation(context, R.anim.anim_up_right)
        val animLeft = AnimationUtils.loadAnimation(context, R.anim.anim_run_right_to_left)
        val animRight = AnimationUtils.loadAnimation(context, R.anim.anim_run_left_to_right)
        imgAnim.startAnimation(animUpRight)
        animUpRight.setAnimationListener(this)
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
        isAnim++
        when (isAnim) {
            1 -> {
                val animDownRight = AnimationUtils.loadAnimation(context, R.anim.anim_down_right)
                animDownRight.setAnimationListener(this)
                imgAnim.startAnimation(animDownRight)
            }
            2 -> {
                val animUpLeft = AnimationUtils.loadAnimation(context, R.anim.anim_down_left)
                animUpLeft.setAnimationListener(this)
                imgAnim.startAnimation(animUpLeft)
                imgAnim.rotationY = 180f
            }
            3 -> {
                val animDownLeft = AnimationUtils.loadAnimation(context, R.anim.anim_up_left)
                animDownLeft.setAnimationListener(this)
                imgAnim.startAnimation(animDownLeft)
            }
            4 -> {
                val animUpRight = AnimationUtils.loadAnimation(context, R.anim.anim_up_right)
                animUpRight.setAnimationListener(this)
                imgAnim.startAnimation(animUpRight)
                imgAnim.rotationY = 360f
            }
        }
        if (isAnim == 4) isAnim = 0
        //Rotate animation
//                var rotate = RotateAnimation(0f, 180f,
//                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
//                rotate.duration = 10000
//                imgAnim.startAnimation(rotate)
    }

    override fun onAnimationStart(animation: Animation?) {
    }

    companion object {
        const val TAG = "AnimationFragment"
    }
}