package br.com.poccompose.real.enums

import br.com.poccompose.R
import br.com.poccompose.application.App

//TODO See it later at iOS
enum class TypeUserEnum(val idEnum:Int, val desc:String){



    ADMIN(1, App.getString(R.string.TypeUserEnum_ADMIN)) {
        fun getId(): Int {
            return this.idEnum
        }
    }, PARTNER(2,App.getString(R.string.TypeUserEnum_PARTNER)) {
        fun getId(): Int {
            return this.idEnum
        }
    },VENDOR(3,App.getString(R.string.TypeUserEnum_VENDOR)) {
        fun getId(): Int {
            return this.idEnum
        }
    };
    class Find{
        companion object {
            fun getEnum(id:Int?):TypeUserEnum?{
                val list = TypeUserEnum.values()
                for (item in list) {
                    if (item.idEnum == id ){
                        return item
                    }
                }
                return null
            }
        }

    }

    fun getNameImage():String {

        return when (this) {
            TypeUserEnum.ADMIN -> "icon_admin"
            TypeUserEnum.PARTNER -> "icon_partner"
            TypeUserEnum.VENDOR -> "icon_vendor"
        }
    }
}
