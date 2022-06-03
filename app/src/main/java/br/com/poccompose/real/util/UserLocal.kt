package br.com.poccompose.real.util

import br.com.poccompose.real.enums.UserLocalTypeEnum


class UserLocal {


    var idSeqUser:Int? = null
    var email:String? = null
    var typeUser: UserLocalTypeEnum? = null
    var tokenFirebase:String? = null
    var name:String? = null

    companion object {
        fun findByIdSeq(idSeqUserVar:Int) : UserLocal?{


            return null


        }
    }
}