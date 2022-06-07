package br.com.poccompose.ui.components

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import br.com.poccompose.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun TakeAPicture(
    coroutineScope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    isCameraSelected: MutableState<Boolean>,
    onGalleyResult: (uri: Uri?) -> Unit,
    onCameraResult: (bitmap: Bitmap?) -> Unit
){
    val context = LocalContext.current
    //val bottomSheetModalState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    //val coroutineScope = rememberCoroutineScope()

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ){uri: Uri? ->
        onGalleyResult(uri)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ){ bitmap->
        onCameraResult(bitmap)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ){isGranted ->
        if(isGranted){
            if(isCameraSelected.value){
                cameraLauncher.launch()
            } else {
                galleryLauncher.launch("image/*")
            }
            coroutineScope.launch {
                modalBottomSheetState.hide()
            }
        }else{
            Toast.makeText(context, context.getText(R.string.permission_denied),Toast.LENGTH_SHORT).show()
        }
    }
    
    ModalBottomSheetLayout(
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colors.primary.copy(alpha = 0.08f))
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Add photo!",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        color = MaterialTheme.colors.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                    Divider(modifier = Modifier
                        .height(1.dp)
                        .background(MaterialTheme.colors.primary))
                    Text(text = "Take a photo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            when (PackageManager.PERMISSION_GRANTED) {
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CAMERA
                                ) -> {
                                    cameraLauncher.launch()
                                    coroutineScope.launch {
                                        modalBottomSheetState.hide()
                                    }
                                }
                                else -> {
                                    isCameraSelected.value = true
                                    permissionLauncher.launch(Manifest.permission.CAMERA)
                                }
                            }
                        }
                        .padding(15.dp),color = Color.Black, fontSize = 18.sp,fontFamily = FontFamily.SansSerif)
                    Divider(modifier = Modifier
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray))
                    Text(text = "From Gallery",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                when (PackageManager.PERMISSION_GRANTED) {
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.READ_EXTERNAL_STORAGE
                                    ) -> {
                                        galleryLauncher.launch("image/*")
                                        coroutineScope.launch {
                                            modalBottomSheetState.hide()
                                        }
                                    }
                                    else -> {
                                        isCameraSelected.value = false
                                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                                    }
                                }
                            }
                            .padding(15.dp),color = Color.Black, fontSize = 18.sp,fontFamily = FontFamily.SansSerif
                    )
                    Text(text = "Cancel", modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                            }
                        }
                        .padding(15.dp),color = Color.Black, fontSize = 18.sp,fontFamily = FontFamily.SansSerif
                    )
                }
            }
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 30.dp,topEnd = 30.dp),
        modifier = Modifier.background(MaterialTheme.colors.background.copy(0.06f))
    ) {
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.fillMaxSize()
//            ) {
//            Button(onClick = {
//                coroutineScope.launch {
//                    if(modalBottomSheetState.isVisible){
//                        modalBottomSheetState.hide()
//                    }else{
//                        modalBottomSheetState.show()
//                    }
//                }
//            },
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth(),
//                shape = RoundedCornerShape(8.dp)
//                ) {
//
//                Text(
//                    text = "Take a picture",
//                    modifier = Modifier.padding(8.dp),
//                    textAlign = TextAlign.Center,
//                    color = Color.White
//                )
//
//            }
//        }
    }
}