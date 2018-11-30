package com.andrew.associate.hellokotlin

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.andrew.associate.hellokotlin.R.color.colorAccent
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val NAME = "title"
        const val BADGE = "image"
        const val DESCRIPTION = "description"
    }

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamDescription: TextView

    private var name: String = ""
    private var badge: Int = 0
    private var description: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            scrollView{
                isVerticalScrollBarEnabled = false
                relativeLayout{
                    lparams(width = matchParent, height = wrapContent)

                        linearLayout{
                            lparams(width = matchParent, height = wrapContent)
                            padding = dip(10)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL

                            teamBadge = imageView().
                                lparams(height = dip(75))

                            teamName = textView{
                                this.gravity = Gravity.CENTER
                                textSize = 20f
                                textColor = ContextCompat.getColor(context,colorAccent)
                            }.lparams{
                                topMargin = dip(5)
                            }

                            teamDescription = textView().
                                lparams{
                                topMargin = dip(20)
                            }
                        }
                }

            }

//            padding = dip(16)
//            nameTextView = textView()

            badge = intent.getIntExtra(BADGE,0)
            name = intent.getStringExtra(NAME)
            description = intent.getStringExtra(DESCRIPTION)

//            Picasso.get(teamBadge).load(badge).into(teamBadge)
            Glide.with(teamBadge).load(badge).into(teamBadge)
            teamName.text = name
            teamDescription.text = description


        }

//        val intent = intent
//        name = intent.getStringExtra("name")
//        nameTextView.text = name

    }
}