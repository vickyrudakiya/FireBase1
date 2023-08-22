package com.example.firebase1.ModelClass

class InsertModelClass {

    var name: String = ""
    var addresh: String = ""
    var product : String = ""
    var number : String = ""
    var image : String = ""
    var key : String = ""

    constructor(name: String, addresh: String, product: String, number: String,key : String){

        this.name=name
        this.addresh=addresh
        this.product=product
        this.number=number
        this.key=key
    }
    constructor(){

    }
}