package com.example.maith.sharinginternetaccess.animation.sprite

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.maith.sharinginternetaccess.R
import kotlinx.android.synthetic.main.fragment_animation.*

class AnimationFragment : Fragment() {

    var kangarooAnim = 0
    var birOneAnim = 0
    var birTwoAnim = 0
    private lateinit var kangarooListener: Animation.AnimationListener
    private lateinit var birdOneListener: Animation.AnimationListener
    private lateinit var birdTwoListener: Animation.AnimationListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kangarooListener()
        birdOneListener()
        birdTwoListener()
        configKangaroo()
        configBirdOne()
        configBirdTwo()

    }

    private fun configBirdOne() {
        configAnimList(R.drawable.animation_bird,imgBird)
        val animRight = AnimationUtils.loadAnimation(context, R.anim.anim_run_left_to_right)
        imgBird.startAnimation(animRight)
        animRight.setAnimationListener(birdOneListener)
    }

    private fun configBirdTwo() {
        configAnimList(R.drawable.animation_birds,imgBirdTwo)
        val animLeft = AnimationUtils.loadAnimation(context, R.anim.anim_to_left)
        imgBirdTwo.startAnimation(animLeft)
        animLeft.setAnimationListener(birdTwoListener)
    }

    private fun configKangaroo() {
        configAnimList(R.drawable.animation_kangaroo,imgAnim)
        val animOne = AnimationUtils.loadAnimation(context, R.anim.anim_to_one_pos)
        imgAnim.startAnimation(animOne)
        animOne.setAnimationListener(kangarooListener)
    }

    private fun configAnimList(animation: Int, iamge: ImageView) {
        iamge.setBackgroundResource(animation)
        val frameAnimation = iamge.background as AnimationDrawable
        frameAnimation.start()
    }

    private fun birdOneListener() {
        birdOneListener = object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                birOneAnim++
                when (birOneAnim) {
                    1 -> {
                        val animLeft = AnimationUtils.loadAnimation(context, R.anim.anim_run_right_to_left)
                        animLeft.setAnimationListener(this)
                        imgBird.startAnimation(animLeft)
                        imgBird.rotationY = 180f
                    }
                    2 -> {
                        val animRight = AnimationUtils.loadAnimation(context, R.anim.anim_run_left_to_right)
                        animRight.setAnimationListener(this)
                        imgBird.startAnimation(animRight)
                        imgBird.rotationY = 360f
                        birOneAnim = 0
                    }
                }
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        }
    }

    private fun birdTwoListener() {
        birdTwoListener = object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                birTwoAnim++
                when (birTwoAnim) {
                    1 -> {
                        val animRight = AnimationUtils.loadAnimation(context, R.anim.anim_to_right)
                        animRight.setAnimationListener(this)
                        imgBirdTwo.startAnimation(animRight)
                        imgBirdTwo.rotationY = 180f

                    }
                    2 -> {
                        val animLeft = AnimationUtils.loadAnimation(context, R.anim.anim_to_left)
                        animLeft.setAnimationListener(this)
                        imgBirdTwo.startAnimation(animLeft)
                        imgBirdTwo.rotationY = 360f
                        birTwoAnim = 0
                    }
                }
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        }
    }

    private fun kangarooListener() {
        kangarooListener = object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                kangarooAnim++
                when (kangarooAnim) {
                    1 -> {
                        val animTwo = AnimationUtils.loadAnimation(context, R.anim.anim_to_two_pos)
                        animTwo.setAnimationListener(this)
                        imgAnim.startAnimation(animTwo)
                    }
                    2 -> {
                        val animThree = AnimationUtils.loadAnimation(context, R.anim.anim_to_three_pos)
                        animThree.setAnimationListener(this)
                        imgAnim.startAnimation(animThree)
                    }
                    3 -> {
                        val animFour = AnimationUtils.loadAnimation(context, R.anim.anim_to_four_pos)
                        animFour.setAnimationListener(this)
                        imgAnim.startAnimation(animFour)
                    }
                    4 -> {
                        val animFive = AnimationUtils.loadAnimation(context, R.anim.anim_to_five_pos)
                        animFive.setAnimationListener(this)
                        imgAnim.startAnimation(animFive)
                    }
                    5 -> {
                        val animSix = AnimationUtils.loadAnimation(context, R.anim.anim_to_six_pos)
                        animSix.setAnimationListener(this)
                        imgAnim.startAnimation(animSix)
                    }
                    6 -> {
                        val animSeven = AnimationUtils.loadAnimation(context, R.anim.anim_to_seven_pos)
                        animSeven.setAnimationListener(this)
                        imgAnim.startAnimation(animSeven)
                    }
                    7 -> {
                        val animEight = AnimationUtils.loadAnimation(context, R.anim.anim_to_eight_pos)
                        animEight.setAnimationListener(this)
                        imgAnim.startAnimation(animEight)
                    }
                    8 -> {
                        val animNine = AnimationUtils.loadAnimation(context, R.anim.anim_to_nine_pos)
                        animNine.setAnimationListener(this)
                        imgAnim.startAnimation(animNine)
                        imgAnim.rotationY = 180f
                    }
                    9 -> {
                        val animTen = AnimationUtils.loadAnimation(context, R.anim.anim_to_ten_pos)
                        animTen.setAnimationListener(this)
                        imgAnim.startAnimation(animTen)
                    }
                    10 -> {
                        val animEleven = AnimationUtils.loadAnimation(context, R.anim.anim_to_eleven_pos)
                        animEleven.setAnimationListener(this)
                        imgAnim.startAnimation(animEleven)
                    }
                    11 -> {
                        val animTwelf = AnimationUtils.loadAnimation(context, R.anim.anim_to_twelf_pos)
                        animTwelf.setAnimationListener(this)
                        imgAnim.startAnimation(animTwelf)
                    }
                    12 -> {
                        val animThirteen = AnimationUtils.loadAnimation(context, R.anim.anim_to_thirteen_pos)
                        animThirteen.setAnimationListener(this)
                        imgAnim.startAnimation(animThirteen)
                    }
                    13 -> {
                        val animFourteen = AnimationUtils.loadAnimation(context, R.anim.anim_to_fourteen_pos)
                        animFourteen.setAnimationListener(this)
                        imgAnim.startAnimation(animFourteen)
                    }
                    14 -> {
                        val animFifteen = AnimationUtils.loadAnimation(context, R.anim.anim_to_fifteen_pos)
                        animFifteen.setAnimationListener(this)
                        imgAnim.startAnimation(animFifteen)
                    }
                    15 -> {
                        val animZero = AnimationUtils.loadAnimation(context, R.anim.anim_to_zero_pos)
                        animZero.setAnimationListener(this)
                        imgAnim.startAnimation(animZero)
                    }
                    16 -> {
                        val animOne = AnimationUtils.loadAnimation(context, R.anim.anim_to_one_pos)
                        animOne.setAnimationListener(this)
                        imgAnim.startAnimation(animOne)
                        imgAnim.rotationY = 360f
                    }
                }
                if (kangarooAnim == 16) kangarooAnim = 0
                //Rotate animation
//                var rotate = RotateAnimation(0f, 180f,
//                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
//                rotate.duration = 10000
//                imgAnim.startAnimation(rotate)

            }

            override fun onAnimationStart(animation: Animation?) {
            }
        }


    }


    companion object {
        const val TAG = "AnimationFragment"
    }
}