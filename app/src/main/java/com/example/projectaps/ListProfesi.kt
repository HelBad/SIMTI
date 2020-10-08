package com.example.projectaps

class ListProfesi {
    lateinit var nama:String
    lateinit var image:String
    lateinit var deskripsi:String
    lateinit var standart:String
    lateinit var grade:String

    constructor(){}
    constructor(nama:String, image:String, deskripsi:String, standart:String, grade:String){
        this.nama = nama
        this.image = image
        this.deskripsi = deskripsi
        this.standart = standart
        this.grade = grade
    }
}