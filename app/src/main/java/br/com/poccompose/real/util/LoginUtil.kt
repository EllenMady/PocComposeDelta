package br.com.poccompose.real.util

import android.content.Context
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

    fun logout(context: Context){
        if(isLogged(context)){
            PreferencesUtil.also {
                with(it){
                    setString(context,"", idUser)
                    setString(context,"", userName)
                    setString(context,"", tokenLogin)
                    setString(context,"", nameDatabase)
                    setTypeUserLogged(context)
                    setString(context,"", seqUser)
                    setString(context,"", idStore)
                    setString(context,"", statusCreateServer)
                    setString(context,"", dataLastTryReLogin)
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

    private fun getPreferenceAsStringByKey(context: Context,key: String, defaultValue: String = "") : String{
        PreferencesUtil.also{
            return it.getString(context,key,defaultValue = defaultValue)
        }
    }

    fun setPreferenceAsStringByKey(context: Context,key: String, value: String) {
        PreferencesUtil.also{
            it.setString(context,value,key)
        }
    }

    fun login(context: Context,userInfoLogin: UserInfoLogin){
        PreferencesUtil.also {
            with(it){
                setString(context,userInfoLogin.idUser, idUser)
                setString(context,userInfoLogin.userName, userName)
                setString(context,userInfoLogin.token, tokenLogin)
                setString(context,userInfoLogin.nameDatabase, nameDatabase)
                setString(context,userInfoLogin.idStore, idStore)
                setString(context,userInfoLogin.seqUser, seqUser)
                setTypeUserLogged(context,userInfoLogin.typeUser)
            }
        }
    }

    fun setTypeUserLogged(context: Context,typeUserP:TypeUserEnum? = null){
        PreferencesUtil.also { pref ->
            typeUserP?.let {
                pref.setString(context = context,typeUserP.desc,typeUser)
            }?: pref.setString(context,"",typeUser)
        }
    }

    fun loginFirebaseAuth(context: Context,email:String, password:String) {
        PreferencesUtil.also {
            it.setString(context,email, emailLogin)
            it.setString(context,password,passwordLogin)
        }
    }

    fun getTypeUserLoggged(context: Context): TypeUserEnum? {
        PreferencesUtil.also {
            val typeUserIDSession: String = it.getString(context, typeUser, defaultValue = "0")
            return TypeUserEnum.Find.getEnum(typeUserIDSession.toInt())
        }
    }

    fun isLogged(context: Context) : Boolean {
        PreferencesUtil.also {
            val idUserStr = it.getString(context,idUser)
            if(idUserStr.trim().isNotEmpty()){
                return true
            }
            return false
        }
    }

    fun getNameLogged (context: Context,) : String{
        if(isLogged(context)) {
            val userLocal = UserLocal.findByIdSeq(getSeqUser(context).toInt())
            if (userLocal?.typeUser == UserLocalTypeEnum.ADMIN) {
                return LocalUtil.getString(R.string.administrator)
            }
            return userLocal?.name ?: "User"
        }else{
            return "User"
        }
    }

    fun getIdUsuario(context: Context) : String{
       PreferencesUtil.also {
            return it.getString(context,idUser)
        }
    }

    fun getNameDatabase(context: Context) : String{
        PreferencesUtil.also{
            return it.getString(context,nameDatabase)!!
        }
    }

    fun getToken(context: Context) : String{
        PreferencesUtil.also{
            return it.getString(context,tokenLogin)
        }
    }

    fun getSeqUser(context: Context) : String{
        PreferencesUtil.also{
            return it.getString(context,seqUser,defaultValue = "0")
        }
    }

    fun getIdStore(context: Context) : String{
        PreferencesUtil.also{
            return it.getString(context,idStore)
        }
    }

    fun getPasswordFirebaseAuth(context: Context) : String{
        PreferencesUtil.also{
            return it.getString(context,passwordLogin)
        }
    }

    fun getEmailFirebaseAuth(context: Context) : String{
       return getPreferenceAsStringByKey(context,emailLogin)
    }

    private fun getStatusCreateServer(context: Context) : String{
        // N -> None
        // P -> Processing
        // F -> Finish
        return getPreferenceAsStringByKey(context,statusCreateServer, ServerStatusEnum.INITIALIZE.status)
    }

      fun setStatusCreateServerNotInitialize(context: Context){
        setPreferenceAsStringByKey(context,statusCreateServer, ServerStatusEnum.INITIALIZE.status)
    }

      fun setStatusCreateServerInProcessing(context: Context){
        setPreferenceAsStringByKey(context,statusCreateServer, ServerStatusEnum.IN_PROCESSING.status)
    }

      fun setStatusCreateServerFinish(context: Context){
        setPreferenceAsStringByKey(context,statusCreateServer, ServerStatusEnum.FINISH.status)
    }

    fun isServerInProcessing(context: Context) : Boolean{
        val status = getPreferenceAsStringByKey(context,statusCreateServer, ServerStatusEnum.INITIALIZE.status)
        return status == ServerStatusEnum.IN_PROCESSING.status
    }

    fun isServerNotInitialize(context: Context) : Boolean{
        val status = getPreferenceAsStringByKey (context,statusCreateServer, ServerStatusEnum.INITIALIZE.status)
        return status == ServerStatusEnum.INITIALIZE.status
    }

    fun isServerFinish(context: Context) : Boolean{
        val status = getPreferenceAsStringByKey(context,statusCreateServer, ServerStatusEnum.INITIALIZE.status)
        return status == ServerStatusEnum.FINISH.status
    }

    fun getDataLastTryReLogin(context: Context) : Date?{
        //TODO see it at iOS
        val defaultsVar = getPreferenceAsStringByKey(context,dataLastTryReLogin,"0")
        when(defaultsVar.trim()){
            "0" -> {
                return null
            }
            "" -> {
                return null
            }
        }
        val dateFormated = DateUtil.formatStringToDate(date= defaultsVar, format= DateUtil.YYYY_MM_DD)
            ?: return null
        return DateUtil.setHourZero(date= dateFormated)
    }

    fun setDateLastTryReLogin(context: Context){
        setPreferenceAsStringByKey(context,dataLastTryReLogin,
            DateUtil.formatDateToString(Date(),DateUtil.YYYY_MM_DD).orEmpty())
    }

    fun closeAllLoginAndFinishReplication(context: Context) {
        logoutFirebaseAuth()
        logout(context)
        setStatusCreateServerNotInitialize(context)
    }

    fun setTryLogin(context: Context){
        setPreferenceAsStringByKey(context,tryLoginAndNotFinish,"Y")
    }
    
    fun setOkLogin(context: Context){
        setPreferenceAsStringByKey(context,tryLoginAndNotFinish,"N")
    }

    fun isTryLoginBeforeAndNotFinish(context: Context) : Boolean{
        return getPreferenceAsStringByKey(context,tryLoginAndNotFinish,defaultValue = "N") == "Y"
    }
}

data class UserInfoLogin(
    val idStore:String,
    val idUser:String,
    val userName:String,
    val token:String,
    val nameDatabase:String,
    val typeUser: TypeUserEnum,
    val seqUser:String
)