package br.com.poccompose.real.enums
//Nao alterar Bruno ja fez
enum class UserLocalTypeEnum(val idEnum:Int, val desc:String) {

    ADMIN(0,"ADMIN") {
        fun getId(): Int {
            return this.idEnum
        }
    }, PARTNER(1,"PARTNER") {
        fun getId(): Int {
            return this.idEnum
        }
    },VENDOR(2,"VENDOR") {
        fun getId(): Int {
            return this.idEnum
        }
    };
    class Find{
        companion object {
            fun getEnum(id:Int?):UserLocalTypeEnum?{
                val list = UserLocalTypeEnum.values()
                for (item in list) {
                    if (item.idEnum == id ){
                        return item
                    }
                }
                return null
            }
        }

    }
}