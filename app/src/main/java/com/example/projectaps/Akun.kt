package com.example.projectaps

class Akun {
    lateinit var nim:String
    lateinit var nama:String
    lateinit var email:String
    lateinit var password:String
    lateinit var jk:String
    lateinit var prodi:String
    lateinit var ipk:String
    lateinit var alamat:String
    lateinit var telp:String
    lateinit var img:String

    lateinit var mbinggris:String
    lateinit var mbasisdata:String
    lateinit var mimk:String
    lateinit var mjarkom:String
    lateinit var mmtk:String
    lateinit var mpbo:String
    lateinit var mpdasar:String
    lateinit var mpvisual:String
    lateinit var mpweb:String
    lateinit var mpti:String
    lateinit var mrpl:String
    lateinit var mso:String
    lateinit var mstrukturdata:String

    constructor() {}
    constructor(nim:String, nama:String, email:String, password:String, jk:String, prodi:String, ipk:String, alamat:String, telp:String, img:String,
                mbinggris:String, mbasisdata:String, mimk:String, mjarkom:String, mmtk:String, mpbo:String, mpdasar:String, mpvisual:String, mpweb:String, mpti:String, mrpl:String, mso:String, mstrukturdata:String) {

        this.nim = nim
        this.nama = nama
        this.email = email
        this.password = password
        this.jk = jk
        this.prodi = prodi
        this.ipk = ipk
        this.alamat = alamat
        this.telp = telp
        this.img = img

        this.mbinggris = mbinggris
        this.mbasisdata = mbasisdata
        this.mimk = mimk
        this.mjarkom = mjarkom
        this.mmtk = mmtk
        this.mpbo = mpbo
        this.mpdasar = mpdasar
        this.mpvisual = mpvisual
        this.mpweb = mpweb
        this.mpti = mpti
        this.mrpl = mrpl
        this.mso = mso
        this.mstrukturdata = mstrukturdata
    }
}