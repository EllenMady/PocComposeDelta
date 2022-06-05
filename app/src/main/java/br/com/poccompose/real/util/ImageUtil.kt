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
        return BitmapFactory.decodeResource(resources, R.drawable.ic_out_of_stock)
    }


    /*
    Explicar os dois metodo abaixo
    class func string64ToImage(stringImage:String) -> UIImage{
        let dataDecoded : Data = Data(base64Encoded: stringImage, options: .ignoreUnknownCharacters)!
        return UIImage(data:dataDecoded)!
    }

    class func nsString64ToImage(stringImage:NSString) -> UIImage{
        let image:String = (stringImage as String)
        return string64ToImage(stringImage:image)
    }
     */

    fun imageToString64(image:Bitmap): String{
        ByteArrayOutputStream().let {
            image.compress(Bitmap.CompressFormat.PNG,100,it)
            val byteArray = it.toByteArray()
            return Base64.encodeToString(byteArray,Base64.DEFAULT)
        }
    }

}