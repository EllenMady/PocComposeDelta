package br.com.poccompose.real.util

import br.com.poccompose.R
import br.com.poccompose.real.enums.ServerStatusEnum
import br.com.poccompose.real.enums.TypeUserEnum
import br.com.poccompose.real.enums.UserLocalTypeEnum
import java.util.*

object LoginUtil {

    const val idUser = "IDUSER"
    const val userName = "NAMEUSER"
    const val tokenLogin = "tokenLogin"
    const val nameDatabase = "nameDatabase"
    const val typeUser = "typeUser"
    const val seqUser = "seqUser"
    const val idStore = "idStore"
    const val passwordLogin = "passwordLogin"
    const val emailLogin  = "emailLogin"
    const val statusCreateServer  = "statusCreateServer"
    const val tryLoginAndNotFinish  = "tryLoginAndNotFinish"
    const val dataLastTryReLogin  = "dataLastTryReLogin"
    fun logout(){
        if(isLogged()){
            PreferencesUtil().also {
                with(it){
                    setString("", idUser)
                    setString("", userName)
                    setString("", tokenLogin)
                    setString("", nameDatabase)
                    setTypeUserLogged()
                    setString("", seqUser)
                    setString("", idStore)
                    setString("", statusCreateServer)
                    setString("", dataLastTryReLogin)
                }
            }

        }
    }

    fun logoutFirebaseAuth(){
//        let defaults = UserDefaults.standard
//                defaults.set("", forKey: defaultsKeys.passwordLogin)
//        defaults.set("", forKey: defaultsKeys.emailLogin)
//
//
//        if Auth.auth().currentUser != nil {
//            do {
//                try Auth.auth().signOut()
//                } catch let error {
//                    // handle error here
//                    print("Error trying to sign out of Firebase: \(error.localizedDescription)")
//                }
//            }
        //TODO see how to make it in Android
    }

    private fun getPreferenceAsStringByKey(key: String, defaultValue: String = "") : String{
        PreferencesUtil().also{
            return it.getString(key) ?: defaultValue
        }
    }

    private fun setPreferenceAsStringByKey(key: String, value: String) {
        PreferencesUtil().also{
            it.setString(value,key)
        }
    }

    fun login(idStoreP:String, idUserP:String, userNameP:String, tokenP:String, nameDatabaseP:String, typeUserP: TypeUserEnum, seqUserP:String){


        PreferencesUtil().also {
            with(it){
                setString(idUserP, idUser)
                setString(userNameP, userName)
                setString(tokenP, tokenLogin)
                setString(nameDatabaseP, nameDatabase)
                setTypeUserLogged(typeUserP)
                setString(seqUserP, seqUser)
                setString(idStoreP, idStore)
            }
        }
    }

    fun setTypeUserLogged(typeUserP:TypeUserEnum? = null){
        PreferencesUtil().also { pref ->
            typeUserP?.let {
                pref.setString(typeUserP.description,typeUser)
            }?: pref.setString("",typeUser)
        }
    }

    fun loginFirebaseAuth(email:String,password:String) {
        PreferencesUtil().also {
            it.setString(email, emailLogin)
            it.setString(password,passwordLogin)
        }
    }

    fun getTypeUserLoggged(): TypeUserEnum? {
        PreferencesUtil().also {
            val typeUserIDSession:String? = it.getString(typeUser)
            //TODO See how enum works at iOS
            val typeUserEnum:TypeUserEnum? = null //TypeUserEnum.getEnum(id: Int(typeUserIDSession ?? "0") ?? 0)
            return typeUserEnum
        }
    }

    fun isLogged() : Boolean {
        PreferencesUtil().also {
            val idUserStr = it.getString(idUser)
            if(idUserStr != null && idUserStr.trim().isNotEmpty()){
                return true
            }
            return false
        }
    }

    fun getNameLogged() : String{
        if(isLogged()) {
            val userLocal = UserLocal.findByIdSeq(getSeqUser().toInt())
            if (userLocal?.typeUser == UserLocalTypeEnum.ADMIN) {
                return LocalUtil.getString(R.string.administrator)
            }
            return userLocal?.name ?: "User"
        }else{
            return "User"
        }
    }

    fun getIdUsuario() : String{
       PreferencesUtil().also {
            return it.getString(idUser)!!
        }
    }

    fun getNameDatabase() : String{
        PreferencesUtil().also{
            return it.getString(nameDatabase)!!
        }
    }

    fun getToken() : String{
        PreferencesUtil().also{
            return it.getString(tokenLogin)!!
        }
    }

    fun getSeqUser() : String{ /// codigo do usuario entre 0 a 19
        PreferencesUtil().also{
            return it.getString(seqUser) ?: "0"
        }
    }

    fun getIdStore() : String{
        PreferencesUtil().also{
            return it.getString(idStore)!!
        }
    }

    fun getPasswordFirebaseAuth() : String{
        PreferencesUtil().also{
            return it.getString(passwordLogin)!!
        }
    }

    fun getEmailFirebaseAuth() : String{
       return getPreferenceAsStringByKey(emailLogin)
    }

    private fun getStatusCreateServer() : String{
        // N -> None
        // P -> Processing
        // F -> Finish
        return getPreferenceAsStringByKey(statusCreateServer, "N")
    }

    fun setStatusCreateServerNotInitialize(){
        setPreferenceAsStringByKey(statusCreateServer, ServerStatusEnum.INITIALIZE.status)
    }

    fun setStatusCreateServerInProcessing(){
        setPreferenceAsStringByKey(statusCreateServer, ServerStatusEnum.IN_PROCESSING.status)
    }

    fun setStatusCreateServerFinish(){
        setPreferenceAsStringByKey(statusCreateServer, ServerStatusEnum.FINISH.status)
    }

    fun isServerInProcessing() : Boolean{
        val status = getPreferenceAsStringByKey(statusCreateServer, ServerStatusEnum.INITIALIZE.status)
        return status == ServerStatusEnum.IN_PROCESSING.status
    }

    fun isServerNotInitialize() : Boolean{
        val status = getPreferenceAsStringByKey(statusCreateServer, ServerStatusEnum.INITIALIZE.status)
        return status == ServerStatusEnum.INITIALIZE.status
    }

    fun isServerFinish() : Boolean{
        val status = getPreferenceAsStringByKey(statusCreateServer, ServerStatusEnum.INITIALIZE.status)
        return status == ServerStatusEnum.FINISH.status
    }

    fun getDataLastTryReLogin() : Date?{
        val defaultsVar = getPreferenceAsStringByKey(dataLastTryReLogin,"0")
        when(defaultsVar.trim()){
            "0" -> {
                return null
            }
            "" -> {
                return null
            }
            null -> {
                return null
            }
        }
        val dateFormated = DateUtil.formatStringToDate(date= defaultsVar, format= DateUtil.YYYY_MM_DD)
            ?: return null
        return DateUtil.setHourZero(date= dateFormated)
    }

    fun setDateLastTryReLogin(){
        setPreferenceAsStringByKey(dataLastTryReLogin,
            DateUtil.formatDateToString(Date(),DateUtil.YYYY_MM_DD).orEmpty())
    }

    fun closeAllLoginAndFinishReplication() {
        logoutFirebaseAuth()
        logout()
        setStatusCreateServerNotInitialize()
    }

    fun setTryLogin(){
        setPreferenceAsStringByKey(tryLoginAndNotFinish,"Y")
    }

    fun setOkLogin(){
        setPreferenceAsStringByKey(tryLoginAndNotFinish,"N")
    }

    fun isTryLoginBeforeAndNotFinish() : Boolean{
        return getPreferenceAsStringByKey(tryLoginAndNotFinish,defaultValue = "N") == "Y"
    }

}