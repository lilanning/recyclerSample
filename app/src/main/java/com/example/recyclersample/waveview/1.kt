package com.example.recyclersample.waveview

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


//// Sequentially executes doWorld followed by "Done"
//fun main() = runBlocking {
//    //runBlocking 若要协程进行相关作业，会阻塞当前线程
//    doWorld()
//    println("Done")
//}
//
//// Concurrently executes both sections
//suspend fun doWorld() = coroutineScope { // this: CoroutineScope
//    //  若要协程执行相关作业,会挂起去执行其他非协程作业 挂起并释放底层线程以供其它协程使用
//    launch {
//        delay(2000L)
//        println("World 2")
//    }
//    launch {
//        delay(1000L)
//        println("World 1")
//    }
//    println("Hello")
//}

//fun main() = runBlocking {
//    withTimeout(400L){
//        val launchA = launch {
//            repeat(3) {
//
//                println("launchA - $it")
//                delay(100)
//            }
//        }
//        val launchB = launch {
//            repeat(3) {
//                println("launchB - $it")
//                delay(100)
//
//            }
//        }
//    }
//
//
//}


fun twoSum(nums: IntArray, target: Int): IntArray? {

//    for (i in 0 until nums.size - 1) {
//        for (j in i+1 until nums.size) {
//            if (nums[i] + nums[j] == target) {
//                return intArrayOf(i, j)
//            }
//        }
//    }
//    return null


    val map: MutableMap<Int, Int> = HashMap()
    for (i in 0 until nums.size) {
        if (map.containsKey(target - nums[i])) {
            return intArrayOf(map[target - nums[i]]!!, i)
        }
        map[nums[i]] = i
    }
    throw IllegalArgumentException("No two sum solution")
// for (i in 0 until nums.size - 1) {
    //     for (j in i+1 until nums.size) {
    //         if (nums[i] + nums[j] == target) {
    //             return intArrayOf(i, j)
    //         }
    //     }
    // }
    // return null


}

fun lengthOfLongestSubstring(s: String): Int {
//    if (s.length ==1){
//        return 1
//    }
//    val nums = s.toCharArray()
//    var max = 0
//    val hashMap = hashMapOf<Char,Int>()
//    for (i in 0 until nums.size){
//        if (!hashMap.containsKey(nums[i])){
//            hashMap[nums[i]] = i
//            max = if(hashMap.size>max) hashMap.size else max
//        }
//        for (j in i+1 until nums.size){
//            if (!hashMap.containsKey(nums[j])){
//                hashMap[nums[j]] = j
//                max = if(hashMap.size>max) hashMap.size else max
//            } else{
//                hashMap.clear()
//                break
//            }
//        }
//    }
//    println("$max")
//    return max

    val nums = s.toCharArray()
    var max = 0
    val hashMap = hashMapOf<Char, Int>()
    var start = 0
    for (end in 0 until nums.size) {
        val char = nums[end]
        if (hashMap.containsKey(char)) {
            start = maxOf(hashMap[char]!!, start)
        }
        max = Math.max(max, end - start + 1)
        hashMap[nums[end]] = end + 1
    }
    println("$max")
    return max

}

fun main(args: Array<String>) {
    var listener: ((Int) -> Unit)?
    val lisntener1:((Int) -> Unit) = {
        println("11111111")
    }
    listener = lisntener1
    listener.invoke(1)
    GlobalScope.launch {

    }
}




