package br.com.poccompose.real.util

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import br.com.poccompose.R
import br.com.poccompose.application.App
import java.io.ByteArrayOutputStream

object ImageUtil {


    fun string64ToImage(imageAsBase64: String?) : Bitmap {
        return imageAsBase64?.let {
            val dataDecoded =  Base64.decode(imageAsBase64,Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(dataDecoded,0,dataDecoded.size)
        }?: getImageNoImageProduct()
    }

    fun getImageNoImageProduct(resources: Resources = App.getInstance().resources) : Bitmap{
        return getImageFromResources(resources,R.drawable.ic_out_of_stock)
    }

    fun imageToString64(image:Bitmap): String{
        ByteArrayOutputStream().let {
            image.compress(Bitmap.CompressFormat.PNG,100,it)
            val byteArray = it.toByteArray()
            return Base64.encodeToString(byteArray,Base64.DEFAULT)
        }
    }

    /*
    //TODO usar lib coil
    class func imageFromUrl(url:String) -> UIImage {
        let url = URL(string:url)
        let data = try? Data(contentsOf: url!)
        if(data == nil){
            let imageUI: UIImage = UIImage(named: "NoImage")!
            return imageUI
        }else{
            let imageUI: UIImage = UIImage(data: data!)!
            return imageUI
        }
    }
     */

    fun getImageFromResources(resources: Resources, idRes: Int): Bitmap{
        return BitmapFactory.decodeResource(resources, idRes)
    }

    fun imageYesOrNo(resources: Resources, yes:Boolean) : Bitmap{
        if(yes){
            return getImageFromResources(resources,R.drawable.ic_yes)
        }
        return getImageFromResources(resources,R.drawable.ic_no)
    }

    fun resizeImage(image: Bitmap, newWidth: Float) : Bitmap {
        val scale = newWidth / image.width
        val newHeight = image.height * scale
        return Bitmap.createScaledBitmap(image,newWidth.toInt(),newHeight.toInt(),true)
    }

}