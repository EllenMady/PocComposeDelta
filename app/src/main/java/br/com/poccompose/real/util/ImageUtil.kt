package br.com.poccompose.real.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import br.com.poccompose.R
import br.com.poccompose.application.App
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
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

    /**
    Use Coroutine to use async function
     important: Do not use it in Compose, prefer ImageFromUrl from component.AppImageLoader
     */
    suspend fun imageFromUrl(url:String, context: Context = App.getInstance()): Bitmap {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .allowHardware(false)
            .error(R.drawable.ic_no_image)
            .build()
        val result = (loader.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

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