package com.example.maith.sharinginternetaccess.animation.sprite

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import com.example.maith.sharinginternetaccess.R
import kotlinx.android.synthetic.main.fragment_animation.*

class AnimationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgAnim.setBackgroundResource(R.drawable.animation_run)
        val frameAnimation = imgAnim.background as AnimationDrawable
        frameAnimation.start()
        val animLeft = AnimationUtils.loadAnimation(context, R.anim.anim_run_left_to_right)
        val animRight = AnimationUtils.loadAnimation(context, R.anim.anim_run_right_to_left)
        imgAnim.startAnimation(animLeft)
        animLeft.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                imgAnim.startAnimation(animRight)
                imgAnim.rotationY = 180f

                //Rotate animation
//                var rotate = RotateAnimation(0f, 180f,
//                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
//                rotate.duration = 10000
//                imgAnim.startAnimation(rotate)
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })

        animRight.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                imgAnim.startAnimation(animLeft)
                imgAnim.rotationY = 360f
                imgAnim.rotationX = 90f
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
    }

    companion object {
        const val TAG = "AnimationFragment"
    }
}