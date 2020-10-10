package com.example.junctionxseoul2020.data

import android.os.Bundle
import android.os.Message
import android.util.Log
import com.example.junctionxseoul2020.LoginActivity
import com.google.firebase.database.*
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class PostManager(): Serializable {

    lateinit var handler : LoginActivity.mHandler

    constructor(handler: LoginActivity.mHandler) : this() {
       this.handler = handler
    }

    val posts: ArrayList<Post> = ArrayList()
    var vec : ArrayList<String> = ArrayList()
    lateinit var postDB: DatabaseReference

    // 앱 처음 실행시 DB에서 게시글 정보를 읽어오는 함수
    fun readPost() {
        postDB = FirebaseDatabase.getInstance().getReference("post")

        postDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                Log.d("Log_Post_DB", p0.value.toString())

                val jsonObj = JSONObject(p0.value.toString())
                for( i in jsonObj.keys()){
                    val str = jsonObj[i].toString()
                    vec.add(str.substring(0,1)+"\"pID\":$i,"+str.substring(1))
                }

//                val gson = Gson()
//                val jsonObj = JSONObject(p0.value.toString())
//                for (i in jsonObj.keys()) {
//                    val str = jsonObj[i].toString()
//                    val strr = str.substring(0,1)+"\"pID\":$i,"+str.substring(1)
//                    posts.add(gson.fromJson(strr, Post::class.java))
//                }

                val message: Message = handler.obtainMessage()
                val bundle: Bundle = Bundle()
                bundle.putBoolean("isThreadEnd_post", true)

                message.data = bundle
                handler.sendMessage(message)
            }

            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
            }
        })
    }

    // 유저가 게시글을 작성할 때 사용하는 함수
    fun addPost(uID: Int) {

    }

    // 유저가 자신이 작성한 게시글을 찾고자 할 때 사용하는 함수
    fun findPost() {

    }

    // 일정시간 경과 후에 게시글이 삭제되야 할 때 사용하는 함수
    fun deletePost() {

    }
}