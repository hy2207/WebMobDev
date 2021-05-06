package com.example.pr_recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_rv_item.view.*

class MainRvAdapter(val ctx: Context, val dogList: ArrayList<Dog>):
    RecyclerView.Adapter<MainRvAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        //최초 로딩하여 만들어진 view가 없는경우 xml파일을 inflate하여 viewholder를 생성한다
        val view = LayoutInflater.from(ctx).inflate(R.layout.main_rv_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //위의 oncreateviewholder에서 만든 view와 실제 입력되는 각각의 데이터를 연결함
        holder?.bind(dogList[position])
        TODO("Not yet implemented")
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dogBreed = itemView.findViewById<TextView>(R.id.dogBreedTv)
        val dogAge = itemView.findViewById<TextView>(R.id.dogAgeTv)
        val dogGender = itemView.findViewById<TextView>(R.id.dogGenderTv)

        fun bind (dog: Dog){ //viewholder랑 클래스의 각 변수를 연동하는 역할을
            dogBreed.text = dog.breed
            dogAge.text = dog.age
            dogGender.text = dog.gender
        }
    }
}

