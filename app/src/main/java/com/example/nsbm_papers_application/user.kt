package com.example.nsbm_papers_application

class user {
    var fristname: String? = null
    var lastname:String?= null
    var email: String? = null
    var batch:String?=null
    var password:String?=null
    var uid: String? = null
    constructor(){}
    constructor(fristname:String?,lastname:String?,email:String?, batch:String? ,password:String? ,uid:String?)
    {
        this.fristname = fristname
        this.lastname= lastname
        this.email = email
        this.batch=batch
        this.password=password
        this.uid = uid
    }
}